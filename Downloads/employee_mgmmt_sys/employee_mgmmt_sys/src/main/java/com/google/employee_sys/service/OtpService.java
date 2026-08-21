package com.google.employee_sys.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.google.employee_sys.dto.VerifyOtpRequest;
import com.google.employee_sys.entity.User;
import com.google.employee_sys.exception.InvalidOtpException;
import com.google.employee_sys.exception.OtpExpiredException;
import com.google.employee_sys.exception.UserNotFoundException;
import com.google.employee_sys.repository.UserRepo;

import jakarta.transaction.Transactional;

@Service
public class OtpService {
	private UserRepo userRepo;
	
	public OtpService(UserRepo userRepo) {
		this.userRepo = userRepo;
	}
	
	
	@Transactional
	public String verifyOtp(VerifyOtpRequest verifyOtpRequest) {
		Optional<User> optionalUser =
                userRepo.findByEmail(verifyOtpRequest.getEmail());
		if(optionalUser.isEmpty()) {
			throw new UserNotFoundException("no user found with this email" +verifyOtpRequest.getEmail());
		}
		
		User user = optionalUser.get();
		if(user.getOtp()==null||user.getOtpExpiryTime()==null) {
			return "otp already verified";
		}
		if(verifyOtpRequest.getOtp()==null) {
			return "otp is required please enter otp";
		}
		
		if(!user.getOtp().equals(verifyOtpRequest.getOtp())) {
			throw new InvalidOtpException("invalid otp");
		}
		
		if(LocalDateTime.now().isAfter(user.getOtpExpiryTime())) {
			user.setOtp(null);
			user.setOtpExpiryTime(null);
			userRepo.save(user);			
			throw new OtpExpiredException("otp expired please request a new otp by choosing the resend-otp end point");
		}
		
		user.setVerified(true);
		user.setOtp(null);
		user.setOtpExpiryTime(null);	
		userRepo.save(user);
		return "otp verified successfully";
	}
}
