package com.techservices.usermanagement.errors.exceptions;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.techservices.usermanagement.errors.ErrorResponse;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(BadRequestException.class)
  public ResponseEntity<ErrorResponse> handleBadRequest(BadRequestException ex) {
    ErrorResponse error = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(NotFoundException.class)
  public ResponseEntity<ErrorResponse> handleNotFound(NotFoundException ex) {
    ErrorResponse error = new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
    return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ErrorResponse> handleValidationException(MethodArgumentNotValidException ex) {
    StringBuilder errors = new StringBuilder();
    for (FieldError fieldError : ex.getBindingResult()
        .getFieldErrors()) {
      errors.append(fieldError.getField())
          .append(": ")
          .append(fieldError.getDefaultMessage());
    }

    ErrorResponse error = new ErrorResponse(
        HttpStatus.BAD_REQUEST.value(),
        errors.toString()
            .trim()
    );

    return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(DataIntegrityViolationException.class)
  public ResponseEntity<ErrorResponse> handleDataIntegrityViolation(DataIntegrityViolationException ex) {
    String field = "unknown";
    String message = "Unique constraint violation";
    String exMsg = ex.getMostSpecificCause()
        .getMessage();

    if (exMsg != null && exMsg.contains("for key")) {
      int keyIndex = exMsg.indexOf("for key");
      int dotIndex = exMsg.indexOf('.', keyIndex);
      int endIndex = exMsg.indexOf("'", dotIndex);
      if (dotIndex > 0 && endIndex > dotIndex) {
        field = exMsg.substring(dotIndex + 1, endIndex);
      }
      message = "Conflict: " + field + " must be unique.";
    }

    ErrorResponse error = new ErrorResponse(HttpStatus.CONFLICT.value(), message);
    return new ResponseEntity<>(error, HttpStatus.CONFLICT);
  }

}
