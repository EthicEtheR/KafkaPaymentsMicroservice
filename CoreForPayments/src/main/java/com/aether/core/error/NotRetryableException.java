package com.aether.core.error;

public class NotRetryableException extends RuntimeException {
    public NotRetryableException(String message) {
        super(message);
    }
}
