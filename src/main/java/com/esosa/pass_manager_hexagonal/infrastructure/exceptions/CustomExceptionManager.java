package com.esosa.pass_manager_hexagonal.infrastructure.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class CustomExceptionManager {

    @ExceptionHandler(NoSuchElementException.class) @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionPayload handleNoSuchElementException(NoSuchElementException exception) {
        return new ExceptionPayload( exception.getMessage(), HttpStatus.BAD_REQUEST.value() );
    }

}