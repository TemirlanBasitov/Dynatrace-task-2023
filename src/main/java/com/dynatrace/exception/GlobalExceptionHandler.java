package com.dynatrace.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;


import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler()
    public ResponseEntity<ErrorDetails>handleNotFoundException(HttpClientErrorException.NotFound exception, WebRequest req){
        ErrorDetails errorDetails = new ErrorDetails( HttpStatus.NOT_FOUND.value(), "Data not found, please check the correctness of provided information", new Date(), req.getDescription(false) );
        return  new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler()
    public ResponseEntity<ErrorDetails>handleBadRequestException(HttpClientErrorException.BadRequest exception, WebRequest request){
        ErrorDetails errorDetails = new ErrorDetails(HttpStatus.BAD_REQUEST.value(), exception.getMessage(), new Date(), request.getDescription(false) );
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(WeekendException.class)
    public ResponseEntity<ErrorDetails> handleWeekendException( WeekendException exception, WebRequest request){
        ErrorDetails errorDetails = new ErrorDetails(HttpStatus.BAD_REQUEST.value(), exception.getMessage(),new Date(), request.getDescription(false) );
        return  new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(FutureDayException.class)
    public ResponseEntity<ErrorDetails> handleFutureDayException(Exception exception, WebRequest request){
        ErrorDetails errorDetails = new ErrorDetails(HttpStatus.BAD_REQUEST.value(), exception.getMessage(), new Date(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(OutOfRangeQuotation.class)
    public ResponseEntity<ErrorDetails>handleOutOfRangeQuotationException(OutOfRangeQuotation ex, WebRequest req){
        ErrorDetails errorDetails = new ErrorDetails(HttpStatus.BAD_REQUEST.value(), ex.getMessage(), new Date(), req.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler()
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorDetails> handleBadRequestException(Exception e, WebRequest req) {
        ErrorDetails errorDetails = new ErrorDetails(HttpStatus.BAD_REQUEST.value(), "Invalid date format, please use 'YYYY-MM-DD'", new Date(), req.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }



}
