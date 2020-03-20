package com.example.authentication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class AuthenticationApp {
	public static void main(String[] args) {
		SpringApplication.run(AuthenticationApp.class, args);
	}
}
