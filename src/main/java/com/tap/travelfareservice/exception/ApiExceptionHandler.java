package com.tap.travelfareservice.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    // Custom exception handler
    @ExceptionHandler(value = {ApiRequestException.class})
    public ResponseEntity<Object> handleApiRequestException(ApiRequestException e) {
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;

        ApiException apiException = new ApiException(
                e.getMessage(),
                badRequest,
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        return new ResponseEntity<>(apiException, badRequest);
    }

    // Custom BadRequestException handler
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ApiException> handleCustomBadRequestException(BadRequestException ex) {
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;

        ApiException apiException = new ApiException(
                ex.getMessage(),
                badRequest,
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        return new ResponseEntity<>(apiException, badRequest);
    }

    // Custom ResourceNotFoundException handling
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiException> handleCustomResourceNotFoundException(ResourceNotFoundException ex) {
        HttpStatus notFound = HttpStatus.NOT_FOUND;
        ApiException apiException = new ApiException(
                ex.getMessage(),
                notFound,
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        return new ResponseEntity<>(apiException, notFound);
    }

    // Handler for exceptions not covered by the globalHandler
    @ExceptionHandler(ServerInternalException.class)
    public ResponseEntity<ApiException> handleServerInternalException(Exception ex) {
        HttpStatus serverError = HttpStatus.INTERNAL_SERVER_ERROR;
        ApiException apiException = new ApiException(
                ex.getMessage(),
                serverError,
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        return new ResponseEntity<>(apiException, serverError);
    }

    // Handler for exceptions not covered by the globalHandler
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiException> handleGlobalException(Exception ex) {
        HttpStatus serverError = HttpStatus.INTERNAL_SERVER_ERROR;
        ApiException apiException = new ApiException(
                ex.getMessage(),
                serverError,
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        return new ResponseEntity<>(apiException, serverError);
    }

    // Custom message for the call to methods not covered by a Handler
    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers,
                                                                   HttpStatus status, WebRequest request) {
        HttpStatus notFound = HttpStatus.NOT_FOUND;

        ApiException apiException = new ApiException(
                "Unsupported method",
                notFound,
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        return new ResponseEntity<>(apiException, notFound);
    }

    // It handles exceptions from our models' validation
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status,
                                                                  WebRequest request) {
        // 1. Create payload containing exception details about each validation field
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError)error).getField();
            String message = error.getDefaultMessage();

            errors.put(fieldName, message);

        });
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        ValidationException validationException = new ValidationException(
                errors,
                badRequest,
                ZonedDateTime.now(ZoneId.of("Z"))
        );

        // 2. Return response entity
        return new ResponseEntity<>(validationException, badRequest);
    }
}
