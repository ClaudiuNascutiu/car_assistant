package com.example.car_assistant.handler_exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<Object> handleException(CustomException e, WebRequest webRequest) {
        return this.handleExceptionInternal(
                e,
                "The User not found!",
                HttpHeaders.EMPTY,
                HttpStatus.BAD_REQUEST,
                webRequest
        );
    }

}
