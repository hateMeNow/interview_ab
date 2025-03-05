package it.pi.gamma.project.ds.strategy.context;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.pi.gamma.project.ds.strategy.IGammaPlatformStrategy;
import it.pi.gamma.project.ds.strategy.concrete.IntegrateStrategy;
import it.pi.gamma.project.exception.APIException;
import it.pi.gamma.project.exception.constant.api.IAPIException;
import it.pi.gamma.project.model.EGPOperation;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
public class GPContext {
	
	@Autowired
	private IntegrateStrategy integrateStrategy;

	private IGammaPlatformStrategy iGammaPlatformStrategy;
	
	public IGammaPlatformStrategy getGammaPlatformStrategy(EGPOperation operation) throws APIException {
		
		if( EGPOperation.INTEGRATION.getCode().equalsIgnoreCase(operation.getCode())) 
			return integrateStrategy;

		throw new APIException(IAPIException.API_EXCEPTION_CODE_AUTH_STRATEGY_CONTEXT, IAPIException.API_EXCEPTION_MESSAGE_AUTH_STRATEGY_CONTEXT);
	}
	
}
