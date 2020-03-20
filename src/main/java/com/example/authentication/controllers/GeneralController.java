package com.example.authentication.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.authentication.entities.User;
import com.example.authentication.security.JwtTokenProvider;
import com.example.authentication.service.Impl.CustomUserDetailsService;

import net.minidev.json.JSONObject;

@RestController
@RequestMapping(value = "public")
public class GeneralController {
	private static final Logger logger = LoggerFactory.getLogger(GeneralController.class);

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private CustomUserDetailsService userService;

	@Autowired
	private JwtTokenProvider jwtTokenUtil;

	@PostMapping(value = "login")
	public ResponseEntity<?> login(@RequestBody User user) {
		final Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		final String token = jwtTokenUtil.generateToken(authentication);
		JSONObject response = new JSONObject();
		user = userService.findByUsername(user.getUserName());
		response.put("user", user);
		response.put("token", token);
		logger.info("Login Successfully");
		return ResponseEntity.ok(response);
	}

	@PostMapping(value = "signup")
	public ResponseEntity<?> signup(@RequestBody User user) {
		user = userService.createUser(user);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	@GetMapping("check")
	public ResponseEntity<?> healthCheck() {
		return new ResponseEntity<>("I'm Alive!", HttpStatus.OK);
	}
}
