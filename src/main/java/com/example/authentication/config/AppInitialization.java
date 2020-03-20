package com.example.authentication.config;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.authentication.entities.Role;
import com.example.authentication.entities.User;
import com.example.authentication.repositories.RoleRepository;
import com.example.authentication.repositories.UserRepository;
import com.google.common.collect.ImmutableMap;

@Configuration
@PropertySource(value = "classpath:application.properties")
public class AppInitialization {

	private UserRepository userRepository;
	private PasswordEncoder passwordEncoder;
	private RoleRepository roleRepository;

	private static final Map<String, String> initialRoles = ImmutableMap.<String, String>builder().put("ADMIN", "Admin")
			.put("USER", "user").build();

	public AppInitialization(@Autowired UserRepository userRepository, @Autowired PasswordEncoder passwordEncoder,
			@Autowired RoleRepository roleRepository) {
		this.passwordEncoder = passwordEncoder;
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		initializeRoles();
		initializeUser("ADMIN", "admin");
		initializeUser("USER", "user");
	}

	private void initializeRoles() {
		initialRoles.forEach((name, displayName) -> {
			if (roleRepository.findRoleByName(name) == null) {
				Role role = new Role();
				role.setName(name);
				role.setDisplayName(displayName);
				roleRepository.save(role);
			}
		});
	}

	private void initializeUser(String roleName, String username) {
		Optional<User> u = userRepository.findByEmail(username + "@testing.com");
		if (!u.isPresent()) {
			User user = new User();
			user.setUserName(username);
			user.setEmail(username + "@testing.com");
			user.setName(username);
			user.setEnable(true);
			user.setPassword(passwordEncoder.encode("Password1"));
			Role role = roleRepository.findRoleByName(roleName);
			user.setRole(role);
			userRepository.save(user);
		}
	}
}
