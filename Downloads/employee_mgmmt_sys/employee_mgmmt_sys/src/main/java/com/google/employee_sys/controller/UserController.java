package com.google.employee_sys.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.employee_sys.dto.RegisterRequest;
import com.google.employee_sys.dto.VerifyOtpRequest;
import com.google.employee_sys.service.OtpService;
import com.google.employee_sys.service.UserService;

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
	public String register(@RequestBody RegisterRequest registerRequest) {
		return service.register(registerRequest);
	}
	
	@PostMapping("/verify-otp")
	public String verifyOtp(@RequestBody VerifyOtpRequest verifyOtpRequest) {
		return otpService.verifyOtp(verifyOtpRequest);
	}
	
	
	

}
