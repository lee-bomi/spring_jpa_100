package com.zerobase.springjpa100.user.exception;

public class ExistsEmailException extends RuntimeException {
    public ExistsEmailException(String message) {
        super(message);
    }
}
