package it.pi.gamma.project.cotroller;

import it.pi.gamma.project.util.Utils;

public class AGPController {

	public String getUuid() {
		return Utils.uuidTransaction();
	}
	
}
