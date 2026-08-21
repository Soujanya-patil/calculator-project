package com.google.employee_sys.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
	private static final Logger log = LoggerFactory.getLogger(EmailService.class);

	private final JavaMailSender mailSender;

	// Falls back to the account configured in application.properties
	// (spring.mail.username) unless app.otp.fallback-email is set separately.
	@Value("${app.otp.fallback-email:${spring.mail.username}}")
	private String fallbackEmail;

	public EmailService(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	public void sendotp(String toEmail, String otp) {
		try {
			send(toEmail, "Your otp is" + " " + otp);
		} catch (MailException ex) {
			// toEmail was invalid/unreachable (bad format, non-existent, rejected by
			// the SMTP server, etc). Re-route the OTP to the configured fallback
			// address instead of losing it, and log which address it was meant for.
			log.warn("Failed to send OTP to '{}', falling back to '{}': {}", toEmail, fallbackEmail, ex.getMessage());
			send(fallbackEmail, "Your otp is " + otp + " (this OTP was intended for registration email: " + toEmail + ")");
		}
	}

	private void send(String toEmail, String body) {
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		simpleMailMessage.setTo(toEmail);
		simpleMailMessage.setSubject("OTP Verification");
		simpleMailMessage.setText(body);

		mailSender.send(simpleMailMessage);
	}

}