package it.pi.gamma.project.ds.strategy;

import it.pi.gamma.project.exception.APIException;
import it.pi.gamma.project.model.GPOperation;

public interface IGammaPlatformStrategy {

	void excecute(GPOperation gpOperation) throws APIException;
	
}
