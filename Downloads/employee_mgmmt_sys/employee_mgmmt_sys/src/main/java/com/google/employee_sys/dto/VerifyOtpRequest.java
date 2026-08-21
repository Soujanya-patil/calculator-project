package com.google.employee_sys.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class VerifyOtpRequest {
	 @Email(message = "Enter valid email")
	    @NotBlank(message = "Email cannot be empty")
	    private String email;

	    @NotBlank(message = "OTP cannot be empty")
	    private String otp;
	    }
