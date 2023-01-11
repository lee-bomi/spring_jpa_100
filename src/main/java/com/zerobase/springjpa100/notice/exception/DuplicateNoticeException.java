package com.zerobase.springjpa100.notice.exception;

public class DuplicateNoticeException extends RuntimeException {
    public DuplicateNoticeException(String message) {
        super(message);
    }
}
