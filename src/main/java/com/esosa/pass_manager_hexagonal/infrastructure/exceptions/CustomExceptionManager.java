package com.esosa.pass_manager_hexagonal.infrastructure.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class CustomExceptionManager {

    @ExceptionHandler(NoSuchElementException.class) @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionPayload handleNoSuchElementException(NoSuchElementException exception) {
        return new ExceptionPayload( exception.getMessage(), HttpStatus.BAD_REQUEST.value() );
    }

    @ExceptionHandler(ResponseStatusException.class) @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionPayload handleResponseStatusException(ResponseStatusException exception) {
        return new ExceptionPayload( exception.getMessage(), HttpStatus.BAD_REQUEST.value() );
    }

}