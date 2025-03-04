package it.pi.gamma.project.v1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import it.pi.gamma.project.ds.strategy.context.GPContext;
import it.pi.gamma.project.exception.APIException;
import it.pi.gamma.project.exception.constant.api.IAPIException;
import it.pi.gamma.project.model.GPOperation;
import it.pi.gamma.project.model.GPResponse;
import it.pi.gamma.project.model.Login;
import it.pi.gamma.project.service.IService;
import it.pi.gamma.project.util.RestUtil;
import it.pi.gamma.project.util.Utils;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class APIGatewayService implements IService{
	
	@Value("${gamma.platform.authentication-url}")
	private String authUrl;
	
	@Autowired
	private RestUtil restUtil;
	
	@Autowired
	private GPContext gpContext;

	public GPResponse<Object> login(Login operation) throws APIException{
		
		log.info("[INFO] Entering method: login. Params [operation: "+operation+"]. Start at: "+Utils.getCurrentTimeStamp());
		
		GPResponse<Object> output = null;
		
		try {
			output = restUtil.execute(authUrl, Utils.parseObjectToJson(operation), GPResponse.class);
		} catch (Exception exception) {
			log.error("[ERROR] Exception execute, cause: "+exception.getCause()+", message: "+exception.getMessage());
			throw new APIException(IAPIException.API_EXCEPTION_CODE_AUTH_COMUNICATION, IAPIException.API_EXCEPTION_MESSAGE_AUTH_COMUNICATION);
		} 
		log.info("[INFO] method login with uuid :"+operation.getUuid()+".Finish at: "+Utils.getCurrentTimeStamp());
		return output;
	}
	
	
	public GPResponse<Object> operation(GPOperation operation) throws APIException{
		
		log.info("[INFO] Entering method: login. Params [operation: "+operation+"]. Start at: "+Utils.getCurrentTimeStamp());
		
		GPResponse<Object> output = null;

		try {
			gpContext.getCashierStrategy(operation.getOperation()).excecute(operation);
		}catch(APIException apiException) {
			log.error("[ERROR] excecute APIException. Code: "+apiException.getCode()+", message: "+apiException.getMessage()+".");
			throw new APIException(IAPIException.API_EXCEPTION_CODE_AUTH_COMUNICATION, IAPIException.API_EXCEPTION_MESSAGE_AUTH_COMUNICATION);
		}
		
		log.info("[INFO] method login with uuid :"+operation.getUuid()+".Finish at: "+Utils.getCurrentTimeStamp());
		return output;
	}
}
