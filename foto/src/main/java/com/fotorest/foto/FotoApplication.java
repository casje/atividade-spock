package com.fotorest.foto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class FotoApplication {

	public static void main(String[] args) {
		SpringApplication.run(FotoApplication.class, args);
	}

}
