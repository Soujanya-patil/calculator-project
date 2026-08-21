package com.google.employee_sys.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.employee_sys.dto.RegisterRequest;
import com.google.employee_sys.dto.VerifyOtpRequest;
import com.google.employee_sys.service.OtpService;
import com.google.employee_sys.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {
	
	UserService service;
	OtpService otpService;
	
	
	public UserController(UserService service, OtpService otpService) {
		this.service = service;
		this.otpService = otpService;
		
	}

	@PostMapping("/register")
	public String register(@Valid @RequestBody RegisterRequest registerRequest) {
		return service.register(registerRequest);
	}
	
	@PostMapping("/verify-otp")
	public String verifyOtp(@Valid @RequestBody VerifyOtpRequest verifyOtpRequest) {
		return otpService.verifyOtp(verifyOtpRequest);
	}
	
	@PostMapping("/resend-otp/{email}")
	public String resendOtp(@PathVariable String email) {
		return service.resendOtp(email);
	}
	
	
	

}
