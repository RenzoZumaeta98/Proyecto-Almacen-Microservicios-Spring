package com.proyecto.Proyecto.PApiAlmacen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class PApiAlmacenApplication {

	public static void main(String[] args) {
		SpringApplication.run(PApiAlmacenApplication.class, args);
	}

}
