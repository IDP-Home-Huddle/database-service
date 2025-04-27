package com.mobylab.springbackend.exception;

import com.mobylab.springbackend.exception.entities.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({BadRequestException.class})
    public ResponseEntity<ErrorObject> handleBadRequest(RuntimeException ex, WebRequest request) {
        ErrorObject errorObject = new ErrorObject();

        errorObject
                .setStatusCode(HttpStatus.BAD_REQUEST.value())
                .setMessage(ex.getMessage())
                .setTimestamp(LocalDateTime.now());
        return new ResponseEntity<>(errorObject, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({RoleException.class})
    public ResponseEntity<ErrorObject> handleRoleException(RuntimeException ex, WebRequest request) {
        ErrorObject errorObject = new ErrorObject();

        errorObject
                .setStatusCode(HttpStatus.NOT_FOUND.value())
                .setMessage(ex.getMessage())
                .setTimestamp(LocalDateTime.now());
        return new ResponseEntity<>(errorObject, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({UserException.class})
    public ResponseEntity<ErrorObject> handleUserException(RuntimeException ex, WebRequest request) {
        ErrorObject errorObject = new ErrorObject();

        errorObject
                .setStatusCode(HttpStatus.NOT_FOUND.value())
                .setMessage(ex.getMessage())
                .setTimestamp(LocalDateTime.now());
        return new ResponseEntity<>(errorObject, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({TaskException.class})
    public ResponseEntity<ErrorObject> handleTaskException(RuntimeException ex, WebRequest request) {
        ErrorObject errorObject = new ErrorObject();

        errorObject
                .setStatusCode(HttpStatus.NOT_FOUND.value())
                .setMessage(ex.getMessage())
                .setTimestamp(LocalDateTime.now());
        return new ResponseEntity<>(errorObject, HttpStatus.NOT_FOUND);
    }
}
