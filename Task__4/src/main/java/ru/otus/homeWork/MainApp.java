package ru.otus.homeWork;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import ru.otus.homeWork.configuration.TestingAppProps;

@SpringBootApplication
@EnableConfigurationProperties(TestingAppProps.class)
public class MainApp {

	public static void main(String[] args) {
		SpringApplication.run(MainApp.class, args);
	}
}