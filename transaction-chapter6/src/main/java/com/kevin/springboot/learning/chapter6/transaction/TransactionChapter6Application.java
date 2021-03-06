package com.kevin.springboot.learning.chapter6.transaction;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class TransactionChapter6Application {

	public static void main(String[] args) {
		SpringApplication.run(TransactionChapter6Application.class, args);
	}

}

