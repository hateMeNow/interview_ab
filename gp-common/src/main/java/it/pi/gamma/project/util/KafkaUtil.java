package it.pi.gamma.project.util;

import it.pi.gamma.project.model.GPEvent;
import it.pi.gamma.project.model.GPOperation;

public class KafkaUtil {

	public static GPEvent getEvent(GPOperation operation, String message) {
		
		GPEvent event = new GPEvent();
		
		event.setUuid(operation.getUuid());
		
		event.setEventType(operation.getOperation().getDescription());
		
		event.setMessage(message);
		
		event.setDate(Utils.getCurrentTimeStamp());
		
		return event;
	}
	
}
