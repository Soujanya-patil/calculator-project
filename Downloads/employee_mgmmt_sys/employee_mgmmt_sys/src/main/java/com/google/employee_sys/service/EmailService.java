package com.google.employee_sys.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
	private final JavaMailSender mailSender;

	public EmailService(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}
	 
	public void sendotp(String toEmail, String otp) {
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		simpleMailMessage.setTo(toEmail);
		simpleMailMessage.setSubject("OTP Verification");
		simpleMailMessage.setText("Your otp is"+" "+ otp);
	
		mailSender.send(simpleMailMessage);
	}
	

}
