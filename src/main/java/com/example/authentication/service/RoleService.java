package com.example.authentication.service;

import java.util.List;

import com.example.authentication.entities.Role;

public interface RoleService {

	Role create(Role role);

	Role update(Role role);

	Long delete(Long id);

	List<Role> get();

	Role get(Long id);

	Role findByName(String name);

}
