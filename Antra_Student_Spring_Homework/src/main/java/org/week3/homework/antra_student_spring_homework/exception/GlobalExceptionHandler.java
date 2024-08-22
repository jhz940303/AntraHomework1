package org.week3.homework.antra_student_spring_homework.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;

@ControllerAdvice
public class GlobalExceptionHandler {
  @ExceptionHandler(HttpClientErrorException.class)
  public ResponseEntity<String> handleHttpClientErrorException(HttpClientErrorException e) {
    return new ResponseEntity<>("An error occurred while calling the API: " + e.getMessage(),
        HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(RestClientException.class)
  public ResponseEntity<String> handleRestClientException(RestClientException e) {
    return new ResponseEntity<>("A client error occurred: " + e.getMessage(),
        HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<String> handleNormalException(Exception e) {
    return new ResponseEntity<>("An unexpected error occurred: " + e.getMessage(),
        HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
