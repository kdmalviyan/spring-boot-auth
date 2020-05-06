package com.example.authentication.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.authentication.entities.User;
import com.example.authentication.service.UserService;

@RestController
@RequestMapping(value = "user")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("")
	@Secured("ROLE_ADMIN")
	public ResponseEntity<?> createUser(@RequestBody User user) {
		User result = userService.createOrUpdate(user);
		return ResponseEntity.ok(result);
	}

	@PutMapping("")
	@Secured("ROLE_ADMIN")
	public ResponseEntity<?> update(@RequestBody User user) {
		User result = userService.createOrUpdate(user);
		return ResponseEntity.ok(result);
	}

	@Secured({ "ROLE_ADMIN" })
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable("id") Long id) {
		Long result = userService.delete(id);
		return ResponseEntity.ok(result);
	}
	
	@Secured({ "ROLE_ADMIN", "ROLE_USER" })
	@GetMapping("/{id}")
	public ResponseEntity<?> get(@PathVariable("id") Long id) {
		Long result = userService.delete(id);
		return ResponseEntity.ok(result);
	}

	@Secured({ "ROLE_ADMIN", "ROLE_USER" })
	@GetMapping("")
	public ResponseEntity<List<User>> getAllUsers() {
		List<User> users = userService.getUsers();
		return ResponseEntity.ok(users);
	}

}
