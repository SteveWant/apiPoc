package com.apipoc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ApiPocApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiPocApplication.class, args);
	}

}
