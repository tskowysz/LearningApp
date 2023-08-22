package com.example.applearning;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Random;

@SpringBootApplication
public class FilipsAppApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext run = SpringApplication.run(FilipsAppApplication.class, args);
	}
	@Bean
	public Random get(){
		return new Random();
	}

}
