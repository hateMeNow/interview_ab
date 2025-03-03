package it.pi.gamma.project.v1.controller;

import it.pi.gamma.project.util.Utils;

public class AGPController {

	public String getUuid() {
		return Utils.uuidTransaction();
	}
	
}
