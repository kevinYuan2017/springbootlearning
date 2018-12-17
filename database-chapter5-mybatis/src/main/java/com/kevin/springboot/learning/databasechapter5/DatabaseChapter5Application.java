package com.kevin.springboot.learning.databasechapter5;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class DatabaseChapter5Application {

	public static void main(String[] args) {
		SpringApplication.run(DatabaseChapter5Application.class, args);
	}

}

