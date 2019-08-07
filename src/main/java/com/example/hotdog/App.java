package com.example.hotdog;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class App {

	public App() {
		log.info("APP INITIALISED service=hot-dog-service version={}", getClass().getPackage().getImplementationVersion());
	}

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
}
