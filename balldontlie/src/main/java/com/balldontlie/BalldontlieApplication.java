package com.balldontlie;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BalldontlieApplication {

	public static void main(String[] args) {
		SpringApplication.run(BalldontlieApplication.class, args);
	}

}
