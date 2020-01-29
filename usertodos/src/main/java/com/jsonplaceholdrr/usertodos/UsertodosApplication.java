package com.jsonplaceholdrr.usertodos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class UsertodosApplication {

	public static void main(String[] args) {
		SpringApplication.run(UsertodosApplication.class, args);
	}

}
