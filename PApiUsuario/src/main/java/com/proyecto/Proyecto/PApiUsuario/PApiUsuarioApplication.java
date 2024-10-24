package com.proyecto.Proyecto.PApiUsuario;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class PApiUsuarioApplication {

	public static void main(String[] args) {
		SpringApplication.run(PApiUsuarioApplication.class, args);
	}

}
