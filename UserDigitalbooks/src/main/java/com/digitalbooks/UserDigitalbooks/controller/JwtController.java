package com.digitalbooks.UserDigitalbooks.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.digitalbooks.UserDigitalbooks.entity.JwtRequest;
import com.digitalbooks.UserDigitalbooks.entity.JwtResponse;
import com.digitalbooks.UserDigitalbooks.entity.User;
import com.digitalbooks.UserDigitalbooks.security.service.JwtService;
import com.digitalbooks.UserDigitalbooks.security.service.UserService;

@RestController
public class JwtController {

	@Autowired
	private JwtService jwtService;
	@Autowired
	private UserService userService;

	@PostMapping({ "/sign-in" })
	public JwtResponse createJwtToken(@RequestBody JwtRequest jwtRequest) throws Exception {
		return jwtService.createJwtToken(jwtRequest);
	}

	@PostMapping({ "/registerNewUser" })
	public String registerNewUser(@RequestBody User user) {
		if (userService.registerNewUser(user)) {
			
			return "User saved";
		} else {
			
			return "User already present";
		}

	}

}
