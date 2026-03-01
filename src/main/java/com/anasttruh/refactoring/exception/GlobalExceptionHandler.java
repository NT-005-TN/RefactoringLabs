package com.anasttruh.refactoring.exception;

import com.anasttruh.refactoring.dto.AddBookResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Глобальный обработчик исключений для REST API.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<AddBookResponse> handleGeneralException(Exception e) {
        e.printStackTrace();
        return ResponseEntity.internalServerError()
                .body(AddBookResponse.failure(e.getClass().getSimpleName(), e.getMessage()));
    }
}