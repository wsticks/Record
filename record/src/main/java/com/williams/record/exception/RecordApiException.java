package com.williams.record.exception;

public class RecordApiException extends RuntimeException {

    RecordApiException(String message){super(message);}

    RecordApiException(String message, Throwable cause){
        super(message,cause);
        if(this.getCause() == null && cause != null){
            this.initCause(cause);
        }
    }
}
