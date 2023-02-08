package ru.otus.homework.IntegrationApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.integration.annotation.IntegrationComponentScan;

@SpringBootApplication
@IntegrationComponentScan
public class IntegrationApp {

	public static void main(String[] args) {
		SpringApplication.run(IntegrationApp.class, args);
	}
}