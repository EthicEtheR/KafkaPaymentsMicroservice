package com.aether.core.error;

public class RetryableException extends RuntimeException {
    public RetryableException(String message) {
        super(message);
    }
}
