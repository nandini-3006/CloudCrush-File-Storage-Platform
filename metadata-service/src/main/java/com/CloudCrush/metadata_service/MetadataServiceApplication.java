package com.CloudCrush.metadata_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class MetadataServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MetadataServiceApplication.class, args);
	}

}
