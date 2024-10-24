package com.proyecto.Proyecto.PApiGateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class PApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(PApiGatewayApplication.class, args);
	}

}
