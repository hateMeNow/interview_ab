package it.pi.gamma.project.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Login extends GPOperation{

	@NotBlank
	private String email;
	
	@NotBlank
	private String password;

	@Override
	public String toString() {
		return "Login [email=" + email + ", password= *********]";
	}
	
}
