package com.proyecto.Proyecto.PApiProveedor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class PApiProveedorApplication {

	public static void main(String[] args) {
		SpringApplication.run(PApiProveedorApplication.class, args);
	}

}
