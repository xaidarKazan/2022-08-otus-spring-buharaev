package ru.otus.homeWork;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;

@SpringBootApplication
@EnableCircuitBreaker
public class LibraryApp {

	public static void main(String[] args) {
		SpringApplication.run(LibraryApp.class, args);
	}
}