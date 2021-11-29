package com.unistore.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ServerApplication {

	public static void main(String[] args) {
		System.out.println("unistore server");
		SpringApplication.run(ServerApplication.class, args);
	}

}
