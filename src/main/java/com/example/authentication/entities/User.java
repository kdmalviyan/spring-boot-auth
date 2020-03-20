package com.example.authentication.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import lombok.Data;

@Entity
@Table(name = "USER_TABLE")
@Data
public class User implements Serializable, Comparable<User> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false, unique = true)
	private String userName;

	@Column(nullable = false, unique = true)
	private String email;

	@Column(nullable = false)
	@Size(min = 4)
	private String password;

	@Column(nullable = false)
	private boolean enable;

	@OneToOne
	private Role role;

	@Override
	public int compareTo(User o) {
		return o.getId().compareTo(this.id);
	}
}
