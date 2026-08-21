package com.google.employee_sys.exception;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice 
public class GlobalExceptionHandling {
	
	
	@ExceptionHandler(ConstraintViolationException.class)
    public Map<String, Object> handleConstraintViolation(
            ConstraintViolationException ex) {

        Map<String, String> errors = new HashMap<>();

        ex.getConstraintViolations().forEach(error -> {
            errors.put(
                error.getPropertyPath().toString(),
                error.getMessage()
            );
        });

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Validation failed");
        response.put("errors", errors);

        return response;
    }
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> invalidData(
            MethodArgumentNotValidException exception) {

        Map<String, String> message = new HashMap<>();

        List<org.springframework.validation.FieldError> errors =
                exception.getBindingResult().getFieldErrors();

        for (org.springframework.validation.FieldError fe : errors) {
            message.put(fe.getField(), fe.getDefaultMessage());
        }

        return new ResponseEntity<>(
                message,
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> userNotFound(
            UserNotFoundException exception) {

        return new ResponseEntity<>(
                exception.getMessage(),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(InvalidOtpException.class)
    public ResponseEntity<String> invalidOtp(
            InvalidOtpException exception) {

        return new ResponseEntity<>(
                exception.getMessage(),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(OtpVerifiedException.class)
    public ResponseEntity<String> otpVerified(
            OtpVerifiedException exception) {

        return new ResponseEntity<>(
                exception.getMessage(),
                HttpStatus.OK
        );
    }

    @ExceptionHandler(OtpExpiredException.class)
    public ResponseEntity<String> otpExpired(
            OtpExpiredException exception) {

        return new ResponseEntity<>(
                exception.getMessage(),
                HttpStatus.GONE
        );
    }

    @ExceptionHandler(OtpAlreadyVerified.class)
    public ResponseEntity<String> otpAlreadyVerified(
            OtpAlreadyVerified exception) {

        return new ResponseEntity<>(
                exception.getMessage(),
                HttpStatus.ACCEPTED
        );
    }
}
