package com.example.authentication.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "home")
public class HomeController {

	@GetMapping(value = "/admin")
	@Secured("ROLE_ADMIN")
	public ResponseEntity<String> getAdminResponse() {
		return new ResponseEntity<>("This is the admin response", HttpStatus.OK);
	}

	@GetMapping(value = "/user")
	@Secured("ROLE_USER")
	public ResponseEntity<String> getUserResponse() {
		return new ResponseEntity<>("This is the user response", HttpStatus.OK);
	}

	@GetMapping(value = "/general")
	@Secured({ "ROLE_ADMIN", "ROLE_USER" })
	public ResponseEntity<String> getGeneralResponse() {
		return new ResponseEntity<>("This is the general response", HttpStatus.OK);
	}

}
