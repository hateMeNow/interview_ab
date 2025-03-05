package it.pi.gamma.project.ds.strategy.concrete;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import it.pi.gamma.project.ds.strategy.IGammaPlatformStrategy;
import it.pi.gamma.project.exception.APIException;
import it.pi.gamma.project.exception.constant.api.IAPIException;
import it.pi.gamma.project.model.GPOperation;
import it.pi.gamma.project.model.GPResponse;
import it.pi.gamma.project.util.RestUtil;
import it.pi.gamma.project.util.Utils;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ValidationStrategy implements IGammaPlatformStrategy{

	@Value("${gamma.platform.authentication-url}")
	private String authUrl;
	
	@Autowired
	private RestUtil restUtil;
	
	@Override
	public GPResponse<Object> excecute(GPOperation gpOperation) throws APIException{
		log.info("[INFO] Entering method: excecute. Params [uuid: "+gpOperation.getUuid()+" ]. Start at: "+Utils.getCurrentTimeStamp());
	
		GPResponse<Object> output = null;
		try {
			output = restUtil.execute(authUrl, Utils.parseObjectToJson(gpOperation), GPResponse.class);
		} catch (Exception exception) {
			log.error("[ERROR] Exception execute, cause: "+exception.getCause()+", message: "+exception.getMessage());
			throw new APIException(IAPIException.API_EXCEPTION_CODE_AUTH_COMUNICATION, IAPIException.API_EXCEPTION_MESSAGE_AUTH_COMUNICATION);
		} 
		
		log.info("[INFO] Entering method: excecute. Params [uuid: "+gpOperation.getUuid()+" ]. Finish at: "+Utils.getCurrentTimeStamp());
		return output;
	}

}
