package com.tms.toll.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class TollServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TollServiceApplication.class, args);
	}

}
