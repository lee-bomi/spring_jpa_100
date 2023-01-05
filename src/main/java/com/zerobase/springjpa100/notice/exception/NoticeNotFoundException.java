package com.zerobase.springjpa100.notice.exception;

public class NoticeNotFoundException extends RuntimeException{

    public NoticeNotFoundException(String message) {
        super(message);
    }
}
