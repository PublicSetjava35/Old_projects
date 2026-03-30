package org.example.AccountService_A.exc;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerException {
    // Обрабатываем исключение
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> illegalArgumentExceptionResponseEntity(IllegalArgumentException exception) {
        return ResponseEntity.badRequest().body(exception.getMessage());
    }

}