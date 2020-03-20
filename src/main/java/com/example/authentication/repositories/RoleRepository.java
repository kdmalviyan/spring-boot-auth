package com.example.authentication.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.authentication.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

	Role findRoleByName(String roleName);

	List<Role> findByNameIn(List<String> names);

	Role findByName(String name);

}
