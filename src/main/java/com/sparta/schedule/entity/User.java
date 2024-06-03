package com.sparta.schedule.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user")
@Getter
@Setter
@NoArgsConstructor
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, unique = true)
	private String nickname;

	@Column(nullable = false)
	private String username;

	@Column(nullable = false)
	private String password;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private UserRoleEnum role;

	private LocalDateTime createdAt = LocalDateTime.now();

	public User(String username, String password, UserRoleEnum role) {
		this.username = username;
		this.password = password;
		this.role = role;
		this.createdAt = LocalDateTime.now();
	}

	public boolean isAdmin() {
		return this.role == UserRoleEnum.ADMIN;
	}
}