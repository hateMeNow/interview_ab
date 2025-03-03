package it.pi.gamma.project.v1.controller;

import java.awt.print.Book;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api-gateway")
public class APIGatewayController {

	@Operation(summary = "Login phase")
	@ApiResponses(value = { 
	  @ApiResponse(responseCode = "200", description = "redirect to Identity provider", 
	    content = { @Content(mediaType = "application/json", 
	      schema = @Schema(implementation = Book.class)) }),
	  @ApiResponse(responseCode = "400", description = "Invalid id supplied", 
	    content = @Content)})
	@GetMapping("/login")
	public void login() {
		log.info("[DEBUG] Entering method: login.");
	}

	@PostMapping("/operation")
	public void operation() {
		log.info("[DEBUG] Entering method: operation.");
	}
}
