package it.pi.gamma.project.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GPLogEvent extends GPEvent{

	private String ip;
	
	private String username;
}
