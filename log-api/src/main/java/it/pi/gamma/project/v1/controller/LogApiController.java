package it.pi.gamma.project.v1.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import it.pi.gamma.project.cotroller.AGPController;
import it.pi.gamma.project.model.GPLogEvent;
import it.pi.gamma.project.util.Utils;
import it.pi.gamma.project.v1.service.LogAPIService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@Tag(name = "Gamma Project Consumer for Log API")
public class LogApiController extends AGPController{
	
	@Autowired
	private LogAPIService logAPIService;

	@Operation(summary = "Login consumer", description = "Log consumer")
	@KafkaListener(topics = "log-topic", groupId = "log-api-group")
	public void logPhase(String message) {
		
		log.info("[INFO] Entering method: logPhase. Params [message: "+message+"]. Start at: "+Utils.getCurrentTimeStamp());
		
		GPLogEvent logEvent = null;
		try {
			logEvent = Utils.parseJsonToObject(message, GPLogEvent.class);
			logAPIService.writeLog(logEvent);
		} catch (IOException ioException) {
			log.error("[ERROR] method: parseJsonToString. Exception: IOException. Cause: "+ioException.getCause()+", message: "+ioException.getMessage());
		}catch(Exception exception) {
			log.error("[ERROR] method: writeLog. Exception: IOException. Cause: "+exception.getCause()+", message: "+exception.getMessage());
		}
		log.info("[INFO] method logPhase. Finish at: "+Utils.getCurrentTimeStamp());
		
	}

}
