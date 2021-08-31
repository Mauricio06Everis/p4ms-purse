package com.example.purse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class MsPurseApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsPurseApplication.class, args);
	}

}
