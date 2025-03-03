package it.pi.gamma.project.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;

@SpringBootApplication
@OpenAPIDefinition(servers = {@Server(url = "${server.servlet.contextPath}", description = "Server URL")})
@ComponentScan(basePackages = "it.pi.gamma.project")
public class GPAPIGatewayApplication {
	public static void main(String[] args) {
		SpringApplication.run(GPAPIGatewayApplication.class, args);
	}
}
