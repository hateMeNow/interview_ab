package it.pi.gamma.project.ds.strategy;

import it.pi.gamma.project.exception.APIException;
import it.pi.gamma.project.model.GPOperation;
import it.pi.gamma.project.model.GPResponse;
import it.pi.gamma.project.model.Header;

public interface IGammaPlatformStrategy {

	GPResponse<Object> excecute(Header header,GPOperation gpOperation) throws APIException;
	
}
