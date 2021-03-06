package com.plexonic.test.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * This class is used for hanling and translating all exceptions.  
 *
 * @author Aram Kirakosyan.
 */
@ControllerAdvice
public class GlobalExceptionHandler {
 
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public String  userException (Exception e) {
        return e.getMessage();
    }
}
