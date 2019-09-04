package com.fbpost.springbootFBCRUDMySql;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages = {"com.example.springbootFBCRUDMySql"})
public class FB_APPLICATION {

	public static void main(String[] args) {
		SpringApplication.run(FB_APPLICATION.class, args);
	}

}
