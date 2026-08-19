package com.google.employee_sys.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.google.employee_sys.dto.VerifyOtpRequest;
import com.google.employee_sys.entity.User;
import com.google.employee_sys.exception.InvalidOtpException;
import com.google.employee_sys.exception.OtpAlreadyVerified;
import com.google.employee_sys.exception.OtpExpiredException;
import com.google.employee_sys.exception.UserNotFoundException;
import com.google.employee_sys.repository.UserRepo;

@Service
public class OtpService {
	private UserRepo userRepo;

	public OtpService(UserRepo userRepo) {
		this.userRepo = userRepo;
	}
	
	public String verifyOtp(VerifyOtpRequest verifyOtpRequest) {
		Optional<User> optionalUser =
                userRepo.findByEmail(verifyOtpRequest.getEmail());

        if (optionalUser.isPresent()) {

            User user = optionalUser.get();

            // OTP is null means user has already been verified
            if (user.getOtp() == null) {
                throw new OtpAlreadyVerified("OTP Already Verified");
            }

            // Check OTP
            if (!user.getOtp().equals(verifyOtpRequest.getOtp())) {
                throw new InvalidOtpException("Invalid OTP");
            }

            // Check OTP expiry
            if (LocalDateTime.now().isAfter(user.getOtpExpiryTime())) {
                throw new OtpExpiredException("OTP expired");
            }

            // Verification successful
            user.setVerified(true);
            user.setOtp(null);
            user.setOtpExpiryTime(null);

            userRepo.save(user);

            return "OTP verified successfully";
        }

        throw new UserNotFoundException("User not found");
    }
	

}
