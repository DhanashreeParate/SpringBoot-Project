package com.enpointe.employeeManagementSystem.error.handlers;

import com.enpointe.employeeManagementSystem.model.SyncResponse;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
@Slf4j // lombok annotation for logger - avoid writing private static final log variable
public class CustomizeExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<SyncResponse> handleValidation(MethodArgumentNotValidException ex){
        String message = ex.getMessage();
        log.warn("Validate the filed: {} ", message);
        return createErrorResponse(HttpStatus.BAD_REQUEST, message);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<SyncResponse> handleNotFound(EntityNotFoundException ex) {
        log.warn("Entity not found: {}", ex.getMessage()); // Logging

        return createErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<SyncResponse> handleGlobalException(Exception ex) {
        log.error("Unhandled exception occurred", ex); //  Logs

        return createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Something went wrong");
    }

    private ResponseEntity<SyncResponse> createErrorResponse(HttpStatus status, String message) {
        SyncResponse response = SyncResponse.builder()
                .status(status.value())
                .error(status.getReasonPhrase())
                .message(message)
                .timeStamp(LocalDateTime.now())
                .build();

        return new ResponseEntity<>(response, status);
    }

}
