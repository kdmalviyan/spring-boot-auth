package com.example.authentication.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "home")
public class HomeController {

	@GetMapping
	public ResponseEntity<?> home(){
		return new ResponseEntity<>("Home", HttpStatus.OK);
	}

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value="/admin", method = RequestMethod.GET)
    public ResponseEntity<?> adminHome(){
        return ResponseEntity.ok("Working");
    }

    
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public ResponseEntity<?> UserHome(){
    	return ResponseEntity.ok("Working");
    }

}
