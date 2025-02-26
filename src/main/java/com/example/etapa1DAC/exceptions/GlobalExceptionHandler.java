package com.example.etapa1DAC.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EventDateConflictException.class)
    public ResponseEntity<Map<String, Object>> handleEventDateConflictException(EventDateConflictException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", LocalDateTime.now());
        response.put("status", HttpStatus.BAD_REQUEST.value());
        response.put("message", ex.getMessage());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ErrorResponse> handleResponseStatusException(ResponseStatusException ex) {
        ErrorResponse error = ErrorResponse.builder()
                .status(ex.getStatusCode().value())
                .error(ex.getReason())
                .message(ex.getMessage())
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.status(ex.getStatusCode().value()).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        List<String> errors = getError(ex);

        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(status.value())
                .error(status.getReasonPhrase())
                .errors(errors)
                .build();

        return ResponseEntity.status(errorResponse.getStatus()).body(errorResponse);
    }

    private List<String> getError(MethodArgumentNotValidException ex) {
        return ex.getBindingResult().getAllErrors().stream()
                .map(this::getErrorMessage)
                .toList();
    }

    private String getErrorMessage(ObjectError error) {
        if (error instanceof FieldError fieldError) {
            return fieldError.getField() + ": " + fieldError.getDefaultMessage();
        }
        return error.getDefaultMessage();
    }
}
