package com.example.authentication.service.Impl;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.authentication.entities.User;
import com.example.authentication.repositories.UserRepository;
import com.example.authentication.service.RoleService;
import com.example.authentication.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private BCryptPasswordEncoder encoder;

	@Autowired
	private UserRepository repository;

	@Autowired
	private RoleService roleService;

	private Pattern BCRYPT_PATTERN = Pattern.compile("\\A\\$2a?\\$\\d\\d\\$[./0-9A-Za-z]{53}");

	@Override
	public Optional<User> findById(Long userId) {
		return repository.findById(userId);
	}

	@Override
	public User createOrUpdate(User user) {
		if (!BCRYPT_PATTERN.matcher(user.getPassword()).matches()) {
			user.setPassword(encoder.encode(user.getPassword()));
		}
		user = repository.save(user);
		return user;
	}

	@Override
	public Long delete(Long id) {
		Optional<User> userOptional = repository.findById(id);
		if (userOptional.isPresent()) {
			User user = userOptional.get();
			user.setEnable(false);
			repository.save(user);
			return id;
		}
		return (long) 0;
	}

	@Override
	public List<User> getUsers() {
		List<User> allUsers = repository.findAll();
		return allUsers;
	}

	@Override
	public User findByUserName(String username) {
		return repository.findByUserName(username).orElse(null);
	}

	@Override
	public User findLoggedInUser() {
		org.springframework.security.core.userdetails.User loggedInUser = (org.springframework.security.core.userdetails.User) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();
		User user = findByUserName(loggedInUser.getUsername());
		return user;
	}

	@Override
	public Collection<? extends User> findByRoleName(String name) {
		List<User> users = repository.findByRole(roleService.findByName(name));
		return users;
	}

}
