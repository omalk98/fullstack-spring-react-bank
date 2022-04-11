package com.bank.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

/**
 * Main Server Launcher
 */
@SpringBootApplication
public class BankSystemApplication {
	/**
	 * Main launcher function
	 * @param args command line args
	 */
	public static void main(String[] args) {
		SpringApplication.run(BankSystemApplication.class, args);
	}
}
