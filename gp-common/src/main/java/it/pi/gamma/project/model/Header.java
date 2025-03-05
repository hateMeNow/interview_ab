package it.pi.gamma.project.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Header {

	public interface X_GP_ACCESS_TOKEN 		{ String headerName = "X-GP-Access-Token";}

	private String accessToken;
}
