package com.qa.account;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class AccServiceMysqlApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccServiceMysqlApplication.class, args);
	}
}
