package it.pi.gamma.project.v1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import it.pi.gamma.project.cotroller.AGPController;
import it.pi.gamma.project.exception.AuthException;
import it.pi.gamma.project.model.GPOperation;
import it.pi.gamma.project.model.GPResponse;
import it.pi.gamma.project.model.Header;
import it.pi.gamma.project.model.Login;
import it.pi.gamma.project.util.Utils;
import it.pi.gamma.project.v1.service.AuthAPIService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@Tag(name = "Gamma Project Authentication API")
@RequestMapping("/auth-api/v1")
public class AuthApiController extends AGPController{
	
	@Autowired
	private AuthAPIService authAPIService;

	@Operation(summary = "Login phase", description = "Check customer login")
	@ApiResponses(value = { 
	  @ApiResponse(responseCode = "200", description = "redirect to Identity provider", 
	    content = { @Content(mediaType = "application/json", 
	    schema = @Schema(implementation = GPResponse.class)) }),
	  @ApiResponse(responseCode = "400", description = "Invalid", 
	    content = @Content(mediaType = "application/json", 
	    schema = @Schema(implementation = GPResponse.class)))})
	@GetMapping(value = "/login", 
			   consumes = MediaType.APPLICATION_JSON_VALUE,
			   produces = MediaType.APPLICATION_JSON_VALUE)
	public GPResponse<Object> login(@Validated Login login) {
		login.setUuid(getUuid());
		log.info("[INFO] Entering method: login. Params [login: "+login+", uuid: "+login.getUuid()+"]. Start at: "+Utils.getCurrentTimeStamp());
		
		GPResponse<Object> response = null;
		
		try {
			response = authAPIService.login(login);
		}catch(AuthException autException) {
			log.error("[ERROR] Exception login, code: "+autException.getCode()+", message: "+autException.getMessage());
			response = new GPResponse<Object>(autException.getCode(), autException.getMessage());
		}
		log.info("[INFO] method login with uuid :"+login.getUuid()+".Finish at: "+Utils.getCurrentTimeStamp());
		return response;
	}

	@Operation(summary = "Integration phase", description = "Integration for Gamma Platform ")
	@ApiResponses(value = { 
	  @ApiResponse(responseCode = "200", description = "Operation success completed", 
	    content = { @Content(mediaType = "application/json", 
	    schema = @Schema(implementation = GPResponse.class)) }),
	  @ApiResponse(responseCode = "400", description = "Invalid", 
	    content = @Content(mediaType = "application/json", 
	    schema = @Schema(implementation = GPResponse.class)))})
	@PostMapping(value = "/integration", 
			   consumes = MediaType.APPLICATION_JSON_VALUE,
			   produces = MediaType.APPLICATION_JSON_VALUE)
	public GPResponse<Object> integration(@RequestBody GPOperation operation) {
		
		log.info("[INFO] Entering method: integration. Params [operation: "+operation+"]. Start at: "+Utils.getCurrentTimeStamp());
		
		GPResponse<Object> response = null;
		
		response = authAPIService.integration(operation);
		
		log.info("[INFO] method operation uuid: "+operation.getUuid()+".Finish at: "+Utils.getCurrentTimeStamp());
		
		return response;
	}
	
	
	@Operation(summary = "validation phase", description = "Validation for Gamma Platform ")
	@ApiResponses(value = { 
	  @ApiResponse(responseCode = "200", description = "Operation success completed", 
	    content = { @Content(mediaType = "application/json", 
	    schema = @Schema(implementation = GPResponse.class)) }),
	  @ApiResponse(responseCode = "400", description = "Invalid", 
	    content = @Content(mediaType = "application/json", 
	    schema = @Schema(implementation = GPResponse.class)))})
	@PostMapping(value = "/validation", headers = {Header.X_GP_ACCESS_TOKEN.headerName}, 
			   consumes = MediaType.APPLICATION_JSON_VALUE,
			   produces = MediaType.APPLICATION_JSON_VALUE)
	public GPResponse<Object> validation(@RequestBody GPOperation operation) {
		
		log.info("[INFO] Entering method: validation. Params [operation: "+operation+"]. Start at: "+Utils.getCurrentTimeStamp());
		
		GPResponse<Object> response = null;
		
		response = authAPIService.validation(operation);
		
		log.info("[INFO] method operation uuid: "+operation.getUuid()+".Finish at: "+Utils.getCurrentTimeStamp());
		
		return response;
	}
}
