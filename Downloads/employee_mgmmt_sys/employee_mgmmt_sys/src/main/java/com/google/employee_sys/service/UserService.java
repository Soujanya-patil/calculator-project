package com.google.employee_sys.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.google.employee_sys.dto.RegisterRequest;
import com.google.employee_sys.repository.UserRepo;

@Service
public class UserService {
	private UserRepo userRepo;

	public UserService(UserRepo userRepo) {
		this.userRepo = userRepo;
	}
	
	public String register(RegisterRequest registerRequest) {
		Optional<com.google.employee_sys.entity.User> oe=userRepo.findByEmail(registerRequest.getEmail());
		if(oe.isPresent()) {
			return "email already exist";
		}
		else {
			com.google.employee_sys.entity.User user= new com.google.employee_sys.entity.User();
			user.setName(registerRequest.getName());
			user.setEmail(registerRequest.getEmail());
			user.setPassword(registerRequest.getPassword());
			user.setRole("USER_ROLE");
			user.setVerified(false);
			userRepo.save(user);
			return "Please enter otp";
		}
	}
	
	
}
