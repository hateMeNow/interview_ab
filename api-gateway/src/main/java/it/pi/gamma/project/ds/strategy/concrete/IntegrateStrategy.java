package it.pi.gamma.project.ds.strategy.concrete;

import org.springframework.stereotype.Component;

import it.pi.gamma.project.ds.strategy.IGammaPlatformStrategy;
import it.pi.gamma.project.exception.APIException;
import it.pi.gamma.project.model.GPOperation;
import it.pi.gamma.project.util.Utils;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class IntegrateStrategy implements IGammaPlatformStrategy{

	@Override
	public void excecute(GPOperation gpOperation) throws APIException{
		log.info("[INFO] Entering method: excecute. Params [uuid: "+gpOperation.getUuid()+" ]. Start at: "+Utils.getCurrentTimeStamp());
	
		
		log.info("[INFO] Entering method: excecute. Params [uuid: "+gpOperation.getUuid()+" ]. Finish at: "+Utils.getCurrentTimeStamp());
	}

}
