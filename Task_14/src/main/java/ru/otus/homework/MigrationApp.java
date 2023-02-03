package ru.otus.homework;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
@SpringBootApplication
public class MigrationApp {

	public static void main(String[] args) {
		SpringApplication.run(MigrationApp.class, args);
	}

}
