package com.workintech.s19d1.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    private ResponseEntity<ExceptionResponse> handleException(ApiException apiException) {
        log.error("ApiException oluştu: {}", apiException.getMessage());
        ExceptionResponse errorResponse = new ExceptionResponse(apiException.getMessage(), apiException.getHttpStatus().value(), LocalDateTime.now());
        return new ResponseEntity<>(errorResponse, apiException.getHttpStatus());
    }

    @ExceptionHandler
    private ResponseEntity<ExceptionResponse> handleGlobalException(Exception exception) {
        log.error("Bilinmeyen hata oluştu: {}", exception.getMessage());
        ExceptionResponse errorResponse = new ExceptionResponse("General Exception occurred", HttpStatus.INTERNAL_SERVER_ERROR.value(),
                LocalDateTime.now());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}