package com.williams.record.exception;

public class ProcessingException extends RecordApiException {

    public ProcessingException(String message) {
        super(message);
    }

    public ProcessingException(String message, Throwable cause) {
        super(message, cause);
    }
}
