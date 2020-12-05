package com.tarasvakaryuk.aett;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class AettApplication {

	public static void main(String[] args) {
		SpringApplication.run(AettApplication.class, args);
	}

}
