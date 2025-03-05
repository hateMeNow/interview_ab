package it.pi.gamma.project.exception;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import it.pi.gamma.project.model.GPResponse;
import it.pi.gamma.project.util.Utils;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class GPAdvice {

	@Value("${application.application-code:876}")
	private String applicationCode;
	
    @ExceptionHandler(APIException.class)
    public ResponseEntity<GPResponse<String>> handleGPException(APIException apiException) {
    	log.debug("[DEBUG] handle AdviceException");
    	GPResponse<String> cashierResponse = new GPResponse<String>(Utils.queueCode(applicationCode, apiException.getCode()), apiException.getMessage());
        return new ResponseEntity<>(cashierResponse, HttpStatus.NOT_ACCEPTABLE);
    }
    
    @ExceptionHandler(AuthException.class)
    public ResponseEntity<GPResponse<String>> handleGPException(AuthException apiException) {
    	log.debug("[DEBUG] handle AdviceException");
    	GPResponse<String> cashierResponse = new GPResponse<String>(Utils.queueCode(applicationCode, apiException.getCode()), apiException.getMessage());
        return new ResponseEntity<>(cashierResponse, HttpStatus.NOT_ACCEPTABLE);
    }
}
