package com.google.employee_sys.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.google.employee_sys.dto.RegisterRequest;
import com.google.employee_sys.repository.UserRepo;
import com.google.employee_sys.util.OtpGenerate;


@Service
public class UserService {
	private UserRepo userRepo;
	private EmailService emailService;
	private PasswordEncoder passwordEncoder;
	
	
	public UserService(UserRepo userRepo, EmailService emailService, PasswordEncoder passwordEncoder) {
		this.userRepo = userRepo;
		this.emailService = emailService;
		this.passwordEncoder = passwordEncoder;
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
			user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
			user.setRole("USER_ROLE");
			user.setVerified(false);
			String otp =OtpGenerate.generateOtp();
			user.setOtp(otp);
			user.setOtpExpiryTime(LocalDateTime.now().plusMinutes(1));
			emailService.sendotp(user.getEmail(), otp);
			userRepo.save(user);
			return "User registered successfully. Please check your email for the OTP.";
		}
		
	}
	public String resendOtp(String email) {
		Optional<com.google.employee_sys.entity.User> oe=userRepo.findByEmail(email);
		if(oe.isEmpty()) {
			return "user not registered please register";
		}
		com.google.employee_sys.entity.User user = oe.get();
		if(!user.isVerified()) {
			String otp=OtpGenerate.generateOtp();
			user.setOtp(otp);
			user.setOtpExpiryTime(LocalDateTime.now().plusMinutes(5));
			emailService.sendotp(user.getEmail(), otp);
			userRepo.save(user);
			return "please check your mail for the otp";
		}
		return "otp already verified";
	}
	
	
}
