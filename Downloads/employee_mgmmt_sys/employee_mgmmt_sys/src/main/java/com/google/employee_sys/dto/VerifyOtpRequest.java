package com.google.employee_sys.dto;

import lombok.Data;

@Data
public class VerifyOtpRequest {
 
	private String email;
	private String otp;
}
