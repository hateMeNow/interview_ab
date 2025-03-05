package it.pi.gamma.project.ds.strategy;

import it.pi.gamma.project.exception.APIException;
import it.pi.gamma.project.model.GPOperation;
import it.pi.gamma.project.model.GPResponse;

public interface IGammaPlatformStrategy {

	GPResponse<Object> excecute(GPOperation gpOperation) throws APIException;
	
}
