package it.pi.gamma.project.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GPEvent {

	private String eventType;
	
	private String description;
	
	private String message;
	
	private String date;
	
	private String uuid;
	
}
