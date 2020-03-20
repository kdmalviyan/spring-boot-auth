package com.example.authentication.service.Impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.authentication.entities.Role;
import com.example.authentication.entities.User;
import com.example.authentication.exceptions.UserDiabledException;
import com.example.authentication.repositories.RoleRepository;
import com.example.authentication.repositories.UserRepository;

@Service
@Transactional
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private RoleRepository roleRepository;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		User user = userRepository.findByUserName(userName).orElse(null);
		if (user == null) {
			throw new UsernameNotFoundException("Username " + userName + " not found");
		} else {
			if (!user.isEnable()) {
				throw new UserDiabledException("User account is disabled, please check with your adin team.");
			}
		}
		return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),
				getAuthorities(user));
	}

	private static Collection<? extends GrantedAuthority> getAuthorities(User user) {
		String userRole = user.getRole().getName();
		return AuthorityUtils.createAuthorityList(userRole);
	}

	public User createUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		Role role = roleRepository.findRoleByName("ADMIN");
		user.setRole(role);
		return userRepository.saveAndFlush(user);
	}

	public User findByUsername(String user) {
		return userRepository.findByUserName(user).orElse(null);
	}
}
