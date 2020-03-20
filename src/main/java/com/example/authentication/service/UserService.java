package com.example.authentication.service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import com.example.authentication.entities.User;

public interface UserService {

	Optional<User> findById(Long userId);

	User createOrUpdate(User user);

	Long delete(Long id);

	List<User> getUsers();

	User findByUserName(String username);

	User findLoggedInUser();

	Collection<? extends User> findByRoleName(String name);

}
