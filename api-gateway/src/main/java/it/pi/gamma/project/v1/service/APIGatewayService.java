package it.pi.gamma.project.v1.service;

import org.springframework.stereotype.Service;

import it.pi.gamma.project.model.GPOperation;
import it.pi.gamma.project.model.GPResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class APIGatewayService<T> {

	public GPResponse<Object> login(GPOperation operation){
		log.info("[INFO] Entering method: login. Params [operation: "+operation+" ]" );
		
		
		
		return null;
	}
	
}
