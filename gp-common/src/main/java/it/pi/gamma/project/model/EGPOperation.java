package it.pi.gamma.project.model;

import java.util.stream.Stream;

import it.pi.gamma.project.exception.APIException;
import it.pi.gamma.project.exception.constant.api.IAPIException;

public enum EGPOperation {

    LOGIN("01", "LOGIN_PHASE"),
    AUTHENTICATION("11", "AUTHENTICATION_PHASE"),
	INTEGRATION("12", "INTEGRATION_PHASE"),
	VALIDATION("91", "VALIDATION_PHASE"),
	READ_MESSAGE("31","READ_MESSAGE"),
	PROCESS_MESSAGE("31","PROCESS_MESSAGE");

    private String code;
    private String description;

    EGPOperation(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return this.code;
    }

    public String getDescription() {
        return this.description;
    }

    public static EGPOperation fromSiteOrigin(String input) throws APIException {
    	return EGPOperation.stream()
    	        .filter(x -> x.code== input).findAny()
    	        .orElseThrow( () -> new APIException(IAPIException.API_EXCEPTION_CODE_OPERATION_NOT_AVAILABLE, IAPIException.API_EXCEPTION_MESSAGE_OPERATION_NOT_AVAILABLE));     
    }

    public static EGPOperation fromMarket(String input) throws APIException {
    	return EGPOperation.stream()
    	        .filter(x -> x.description.equalsIgnoreCase(input)).findAny()
    	        .orElseThrow( () -> new APIException(IAPIException.API_EXCEPTION_CODE_OPERATION_NOT_AVAILABLE, IAPIException.API_EXCEPTION_MESSAGE_OPERATION_NOT_AVAILABLE)); 
    }
    
    private static Stream<EGPOperation> stream() {
        return Stream.of(EGPOperation.values()); 
    }
	
    
}
