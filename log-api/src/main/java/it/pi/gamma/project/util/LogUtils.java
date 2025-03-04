package it.pi.gamma.project.util;

import it.pi.gamma.project.model.GPLogEvent;
import it.pi.gamma.project.v1.entity.GPLog;

public class LogUtils {

	public static GPLog parseGPLog(GPLogEvent input) {
		
		GPLog output = new GPLog();
		
		output.setUsername(input.getUsername());
		
		output.setIp(input.getIp());
		
		output.setDescription(input.getDescription());
		
		output.setMessage(input.getMessage());
		
		output.setTypeEvent(input.getEventType());
		
		return output;
	}
}
