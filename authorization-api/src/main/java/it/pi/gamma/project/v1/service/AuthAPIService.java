package it.pi.gamma.project.v1.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.pi.gamma.project.constant.GPConstant;
import it.pi.gamma.project.exception.AuthException;
import it.pi.gamma.project.exception.constant.auth.IAuthException;
import it.pi.gamma.project.model.GPResponse;
import it.pi.gamma.project.model.Login;
import it.pi.gamma.project.service.IService;
import it.pi.gamma.project.util.Utils;
import it.pi.gamma.project.v1.entity.User;
import it.pi.gamma.project.v1.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AuthAPIService<T> implements IService{

	@Autowired
	private UserRepository userRepository;
	
	public GPResponse<Object> login(Login login){
		log.info("[INFO] Entering method: login. Params [login: "+login+", uuid: "+login.getUuid()+"]. Start at: "+Utils.getCurrentTimeStamp());

		Optional<User> optionalUser =  userRepository.findByEmailAndPassword(login.getEmail(), login.getPassword());
		
		String accessToken = null;
		
		optionalUser.ifPresentOrElse(user -> Utils.generateCustomAccessToken(accessToken, user.getUsername()),
		        () -> {throw new AuthException(IAuthException.AUTH_EXCEPTION_CODE_USER_NOT_FOUND,IAuthException.AUTH_EXCEPTION_MESSAGE_USER_NOT_FOUND);});

		GPResponse<Object> output = generateGPResponse(GPConstant.SUCCESS_CODE, GPConstant.SUCCESS_MESSAGE, accessToken);
		
		log.info("[INFO] method login with uuid :"+login.getUuid()+".Finish at: "+Utils.getCurrentTimeStamp());
		
		return output;
	}
	
}
