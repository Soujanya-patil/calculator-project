package com.google.employee_sys.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.google.employee_sys.dto.RegisterRequest;
import com.google.employee_sys.repository.UserRepo;
import com.google.employee_sys.util.OtpGenerate;

@Service
public class UserService {
	private UserRepo userRepo;
	private EmailService emailService;

	
	public UserService(UserRepo userRepo, EmailService emailService) {
		this.userRepo = userRepo;
		this.emailService = emailService;
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
			String otp =OtpGenerate.generateOtp();
			user.setOtp(otp);
			user.setOtpExpiryTime(LocalDateTime.now().plusMinutes(5));
			userRepo.save(user);

			emailService.sendotp(user.getEmail(), otp);
			return "User registered successfully. Please check your email for the OTP.";
		}
	}
	
	
}
