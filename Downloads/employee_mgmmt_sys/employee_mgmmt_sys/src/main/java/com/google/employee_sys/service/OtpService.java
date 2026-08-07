package com.google.employee_sys.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.google.employee_sys.dto.VerifyOtpRequest;
import com.google.employee_sys.entity.User;
import com.google.employee_sys.repository.UserRepo;

@Service
public class OtpService {
	private UserRepo userRepo;

	public OtpService(UserRepo userRepo) {
		this.userRepo = userRepo;
	}
	
	public String verifyOtp(VerifyOtpRequest verifyOtpRequest) {
		Optional<User> oe= userRepo.findByEmail(verifyOtpRequest.getEmail());
		if(oe.isPresent()) {
			User user=oe.get();
			if(!user.getOtp().equals(verifyOtpRequest.getOtp())) {
				return "Invalid OTP. Please try again.";
			}
			if(user.getOtpExpiryTime().isBefore(java.time.LocalDateTime.now())) {
				return "OTP has expired. Please request a new one.";
			}
			else {
				user.setVerified(true);
				user.setOtp(null);
				user.setOtpExpiryTime(null);
				userRepo.save(user);
				return "OTP verified successfully. Your account is now verified.";
			}
		}
		else {
			return "User not found with the provided email.";
		}
	
	}
	

}
