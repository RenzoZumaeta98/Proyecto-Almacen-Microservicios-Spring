package com.proyecto.Proyecto.PApiCategory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class PApiCategoryApplication {

	public static void main(String[] args) {
		SpringApplication.run(PApiCategoryApplication.class, args);
	}

}
