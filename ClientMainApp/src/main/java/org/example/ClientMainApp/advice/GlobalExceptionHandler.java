package org.example.ClientMainApp.advice;
import org.example.ClientMainApp.dto.ExceptionDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ExceptionDTO> exceptionDTOResponseEntity(IllegalArgumentException exc) {
        return ResponseEntity.badRequest().body(new ExceptionDTO(404, "address not found: " + exc.getMessage()));
    }

}