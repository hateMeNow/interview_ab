package it.pi.gamma.project.v1.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import it.pi.gamma.project.constant.GPConstant;
import it.pi.gamma.project.exception.AuthException;
import it.pi.gamma.project.exception.constant.auth.IAuthException;
import it.pi.gamma.project.model.GPOperation;
import it.pi.gamma.project.model.GPResponse;
import it.pi.gamma.project.model.Login;
import it.pi.gamma.project.service.IService;
import it.pi.gamma.project.util.RestUtil;
import it.pi.gamma.project.util.Utils;
import it.pi.gamma.project.v1.entity.User;
import it.pi.gamma.project.v1.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AuthAPIService implements IService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RestUtil restUtil;
	
	@Value("${gamma.platform.identity-provider-url}")
	private String identityProviderUrl;
	
	@Value("${gamma.platform.client-id}")
	private String clientId;
	
	public GPResponse<Object> login(Login login) throws AuthException{
		log.info("[INFO] Entering method: login. Params [login: "+login+", uuid: "+login.getUuid()+"]. Start at: "+Utils.getCurrentTimeStamp());

		Optional<User> optionalUser =  userRepository.findByEmailAndPassword(login.getEmail(), login.getPassword());
		
		String accessToken = null;
		
		optionalUser.ifPresentOrElse(user -> Utils.generateCustomAccessToken(accessToken, user.getUsername()),
		        () -> {throw new AuthException(IAuthException.AUTH_EXCEPTION_CODE_USER_NOT_FOUND,IAuthException.AUTH_EXCEPTION_MESSAGE_USER_NOT_FOUND);});

		GPResponse<Object> output = generateGPResponse(GPConstant.SUCCESS_CODE, GPConstant.SUCCESS_MESSAGE, accessToken);
		
		log.info("[INFO] method login with uuid :"+login.getUuid()+".Finish at: "+Utils.getCurrentTimeStamp());
		
		return output;
	}
	
	public GPResponse<Object> integration(GPOperation operation) throws AuthException{
		log.info("[INFO] Entering method: integration. Params [operation: "+operation+"]. Start at: "+Utils.getCurrentTimeStamp());
		
		String redirectURL = null;
		
		try {
			redirectURL = restUtil.execute(identityProviderUrl + clientId, null, String.class);
		} catch (Exception exception) {
			log.error("[ERROR] Exception execute, cause: "+exception.getCause()+", message: "+exception.getMessage());
			throw new AuthException(IAuthException.AUTH_EXCEPTION_CODE_INTEGRATION_ERROR, IAuthException.AUTH_EXCEPTION_MESSAGE_INTEGRATION_ERROR);
		}
		GPResponse<Object> output = generateGPResponse(GPConstant.SUCCESS_CODE, GPConstant.SUCCESS_MESSAGE, redirectURL);
		
		log.info("[INFO] method integration with uuid :"+operation.getUuid()+".Finish at: "+Utils.getCurrentTimeStamp());
		
		return output;
	}
	
	public GPResponse<Object> validation(GPOperation operation) throws AuthException{
		log.info("[INFO] Entering method: validation. Params [operation: "+operation+"]. Start at: "+Utils.getCurrentTimeStamp());
		
		String redirectURL = null;
		
		try {
			// TODO Inserimento validazione provider 
//			redirectURL = restUtil.execute(, null, String.class);
		} catch (Exception exception) {
			log.error("[ERROR] Exception execute, cause: "+exception.getCause()+", message: "+exception.getMessage());
			throw new AuthException(IAuthException.AUTH_EXCEPTION_CODE_INTEGRATION_ERROR, IAuthException.AUTH_EXCEPTION_MESSAGE_INTEGRATION_ERROR);
		}
		GPResponse<Object> output = generateGPResponse(GPConstant.SUCCESS_CODE, GPConstant.SUCCESS_MESSAGE, redirectURL);
		
		log.info("[INFO] method validation with uuid :"+operation.getUuid()+".Finish at: "+Utils.getCurrentTimeStamp());
		
		return output;
	}
	
}
