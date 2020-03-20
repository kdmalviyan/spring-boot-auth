package com.example.authentication.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.authentication.entities.Role;
import com.example.authentication.repositories.RoleRepository;
import com.example.authentication.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleRepository roleRepository;

	@Override
	public Role create(Role role) {
		return roleRepository.save(role);
	}

	@Override
	public Role update(Role role) {
		return roleRepository.save(role);
	}

	@Override
	public Long delete(Long id) {
		roleRepository.deleteById(id);
		return id;
	}

	@Override
	public List<Role> get() {
		return roleRepository.findAll();
	}

	@Override
	public Role get(Long id) {
		return roleRepository.findById(id).orElse(null);
	}

	@Override
	public Role findByName(String name) {
		return roleRepository.findByName(name);
	}

}
