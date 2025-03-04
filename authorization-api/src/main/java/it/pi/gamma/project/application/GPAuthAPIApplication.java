package it.pi.gamma.project.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;

@SpringBootApplication
@OpenAPIDefinition(servers = {@Server(url = "${server.servlet.contextPath}", description = "Server URL")})
@EnableJpaRepositories(basePackages ={"it.pi.gamma.project"})
@EntityScan("it.pi.gamma.project")   
@ComponentScan(basePackages = {"it.pi.gamma.project"})
public class GPAuthAPIApplication {
	public static void main(String[] args) {
		SpringApplication.run(GPAuthAPIApplication.class, args);
	}
}
