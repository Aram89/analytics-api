package com.plexonic.test.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Aram Kirakosyan.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Returning bad request status
     * and error string for user exceptions
     * @param e Exception.
     * @return error string in json.
     */

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public String  userException (Exception e) {
        return e.getMessage();
    }


}
