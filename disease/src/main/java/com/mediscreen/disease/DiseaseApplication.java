package com.mediscreen.disease;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients("com.mediscreen.disease")
@EnableDiscoveryClient
public class DiseaseApplication {

	public static void main(String[] args) {
		SpringApplication.run(DiseaseApplication.class, args);
	}

}
