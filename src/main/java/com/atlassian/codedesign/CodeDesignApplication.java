package com.atlassian.codedesign;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Imagine we have a customer support ticketing system. The system allows
 * customers to rate the support agent. To start with, write a function which
 * accepts a rating, and another which will get all of the agents and the
 * average rating each one has received, ordered highest to lowest.
 */

@SpringBootApplication
public class CodeDesignApplication {

	public static void main(String[] args) {
		SpringApplication.run(CodeDesignApplication.class, args);
	}
	

}
