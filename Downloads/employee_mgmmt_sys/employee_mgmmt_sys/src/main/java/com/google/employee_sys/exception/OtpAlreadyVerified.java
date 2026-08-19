package com.google.employee_sys.exception;

public class OtpAlreadyVerified extends RuntimeException {

    public OtpAlreadyVerified(String message) {
        super(message);
    }
}
