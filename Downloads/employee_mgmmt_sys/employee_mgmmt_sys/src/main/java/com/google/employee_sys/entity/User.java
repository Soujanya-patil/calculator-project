package com.google.employee_sys.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity	
@Data
@Table(name="users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotNull
	private String name;
	
	@Email
	@Column(unique = true)
	private String email;
	
	@Size(min = 6, message = "Password must be at least 6 characters long")
	private String password;
	private String role;
	private boolean verified;
	private String otp;
	private LocalDateTime otpExpiryTime;
	
}
