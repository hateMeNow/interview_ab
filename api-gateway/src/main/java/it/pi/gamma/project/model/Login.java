package it.pi.gamma.project.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Login extends GPOperation{

	@NotBlank
	private String username;
	
	@NotBlank
	private String password;

	@Override
	public String toString() {
		return "Login [username=" + username + ", password= *********]";
	}
	
}
