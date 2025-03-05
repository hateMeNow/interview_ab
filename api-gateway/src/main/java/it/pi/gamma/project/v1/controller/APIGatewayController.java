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
import it.pi.gamma.project.exception.APIException;
import it.pi.gamma.project.model.GPOperation;
import it.pi.gamma.project.model.GPResponse;
import it.pi.gamma.project.model.Header;
import it.pi.gamma.project.model.Login;
import it.pi.gamma.project.util.Utils;
import it.pi.gamma.project.v1.service.APIGatewayService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@Tag(name = "Gamma Project Gateway API")
@RequestMapping("/api-gateway/v1")
public class APIGatewayController extends AGPController{

	@Autowired
	private APIGatewayService apiGatewayService;
	
	@Operation(summary = "Login phase", description = "Returns the redirect URL for identity provider")
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
			response = apiGatewayService.login(login);
		}catch(APIException apiException) {
			log.error("[ERROR] Exception login, code: "+apiException.getCode()+", message: "+apiException.getMessage());
			response = new GPResponse<Object>(apiException.getCode(), apiException.getMessage());
		}
		log.info("[INFO] method login with uuid :"+login.getUuid()+".Finish at: "+Utils.getCurrentTimeStamp());
		return response;
	}

	@Operation(summary = "Operation phase", description = "Operation for Gamma Platform ")
	@ApiResponses(value = { 
	  @ApiResponse(responseCode = "200", description = "Operation success completed", 
	    content = { @Content(mediaType = "application/json", 
	    schema = @Schema(implementation = GPResponse.class)) }),
	  @ApiResponse(responseCode = "400", description = "Invalid", 
	    content = @Content(mediaType = "application/json", 
	    schema = @Schema(implementation = GPResponse.class)))})
	@PostMapping(
			   value = "/operation", 
	           headers = {Header.X_GP_ACCESS_TOKEN.headerName}, 
			   consumes = MediaType.APPLICATION_JSON_VALUE,
			   produces = MediaType.APPLICATION_JSON_VALUE
			   )
	public GPResponse<Object> operation(@RequestBody GPOperation operation, Header header) throws APIException {
		operation.setUuid(getUuid());
		log.info("[INFO] Entering method: operation. Params [operation: "+operation+"]. Start at: "+Utils.getCurrentTimeStamp());

		GPResponse<Object> output = null;
			
		output = apiGatewayService.operation(header, operation);
		
		log.info("[INFO] method operation uuid: "+operation.getUuid()+". Finish at: "+Utils.getCurrentTimeStamp());
		
		return output;
	}
}
