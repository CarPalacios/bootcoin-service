package com.nttdata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class BootcoinServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BootcoinServiceApplication.class, args);
	}

}
