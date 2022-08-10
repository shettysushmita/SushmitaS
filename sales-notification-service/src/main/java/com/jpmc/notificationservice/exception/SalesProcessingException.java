package com.jpmc.notificationservice.exception;

public class SalesProcessingException extends RuntimeException {
    public SalesProcessingException(Exception ex) {
        super(ex);
    }

    public SalesProcessingException(String message) {
        super(message);
    }

    public SalesProcessingException(String message, Throwable t) {
        super(message, t);
    }
}
