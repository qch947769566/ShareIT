package com.qch.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableAutoConfiguration
@SpringBootApplication
public class ShareItApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShareItApplication.class, args);
	}

}
