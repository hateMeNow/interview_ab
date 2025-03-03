package it.pi.gamma.project.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GPResponse<T> {

	public GPResponse(String code, String description) {
		this.code = code;
		this.description = description;
	}
	
	private String code;
	
	private String description; 
	
	private T result;
		
}
