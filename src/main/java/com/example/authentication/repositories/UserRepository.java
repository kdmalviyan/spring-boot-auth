package com.example.authentication.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.authentication.entities.Role;
import com.example.authentication.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByEmail(String email);

	Optional<User> findByUserName(String username);

	List<User> findByRoleIn(List<Role> roles);

	List<User> findByRole(Role findByName);

}