package com.aether.TransferService.error;

public class TransferServiceException extends RuntimeException {
    public TransferServiceException(String message) {
        super(message);
    }
}
