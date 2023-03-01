package ru.otus.homeWork;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;

@SpringBootApplication
@EnableCircuitBreaker
public class LibraryMain {

	public static void main(String[] args) {
		SpringApplication.run(LibraryMain.class, args);
	}
}