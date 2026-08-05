package com.google.employee_sys.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.employee_sys.dto.RegisterRequest;
import com.google.employee_sys.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	
	UserService service;

	public UserController(UserService service) {
		this.service = service;
	}
	
	
	@PostMapping("/register")
	public String register(@RequestBody RegisterRequest registerRequest) {
		return service.register(registerRequest);
	}
	
	

}
