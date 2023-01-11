package com.zerobase.springjpa100.user.model;

public class PasswordNotMatchException extends RuntimeException {
    public PasswordNotMatchException(String message) {
        super(message);
    }
}
