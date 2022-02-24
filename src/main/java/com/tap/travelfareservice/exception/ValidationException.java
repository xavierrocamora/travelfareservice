package com.tap.travelfareservice.exception;

import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;
import java.util.Map;

// Exception message with a given format to be displayed on error validation responses
public class ValidationException {
    private final Map<String,String> validationMessage;
    private final HttpStatus httpStatus;
    private final ZonedDateTime timestamp;

    public ValidationException(Map<String,String> validationMessage,
                        HttpStatus httpStatus,
                        ZonedDateTime timestamp) {
        this.validationMessage = validationMessage;
        this.httpStatus = httpStatus;
        this.timestamp = timestamp;
    }

    public Map<String,String> getValidationMessage() {
        return validationMessage;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public ZonedDateTime getTimestamp() {
        return timestamp;
    }
}
