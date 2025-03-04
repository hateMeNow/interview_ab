package it.pi.gamma.project.service;

import it.pi.gamma.project.model.GPResponse;

public interface IService {

	default GPResponse<Object> generateGPResponse(String code, String message, Object result){
		
		GPResponse<Object> gpResponse = new GPResponse<Object>(code, message, result);
		
		return gpResponse;
	}
}
