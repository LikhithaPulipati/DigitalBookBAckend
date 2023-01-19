package com.digitalbooks.UserDigitalbooks;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@SpringBootApplication
@SecurityScheme(
		name = "user_security_scheme", 
		scheme = "bearer", 
		type = SecuritySchemeType.HTTP, 
		in = SecuritySchemeIn.HEADER
	)
@EnableFeignClients
public class UserDigitalbooksApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserDigitalbooksApplication.class, args);
	}

}
