package com.qantas.service.rest.exception;

import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class AirportExceptionHandler extends ResponseEntityExceptionHandler {
  
   //other exception handlers
  
   @ExceptionHandler(AirportBadRequestException.class)
   protected ResponseEntity<Object> handleBadRequestException(
		   AirportBadRequestException ex) {
    
       return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
   }
   
   @ExceptionHandler(AirportServerException.class)
   protected ResponseEntity<Object> handleServerException(
		   AirportServerException ex) {
    
       return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
   }
	
   @Override
   protected ResponseEntity<Object> handleTypeMismatch(
		   TypeMismatchException ex, HttpHeaders headers,
       HttpStatus status, WebRequest request) {
	   return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid request filter value");
   }
}