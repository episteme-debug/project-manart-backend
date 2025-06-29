package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

import java.security.SecureRandom;
import java.util.Base64;

@EnableAsync
@SpringBootApplication
public class ManartApplication {

	public static void main(String[] args) {
		SpringApplication.run(ManartApplication.class, args);

/*
		SecureRandom secureRandom = new SecureRandom();

		byte[] secretKeybytes = new byte[32];
		secureRandom.nextBytes(secretKeybytes);

		String SECRET_KEY = Base64.getEncoder().encodeToString(secretKeybytes);

		System.out.println(SECRET_KEY);
*/

	}

}
