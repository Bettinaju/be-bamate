package com.bamate.bamatebackend.account;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * This class provides advice for handling {@code AccountNotFoundException} across the application.
 * It returns an appropriate HTTP response when an {@code AccountNotFoundException} is thrown.
 */
@ControllerAdvice
class AccountNotFoundAdvice {

    /**
     * Handles {@code AccountNotFoundException} and returns an appropriate HTTP response.
     * @param ex The {@code AccountNotFoundException} that was thrown.
     * @return A String containing the error message from the thrown exception.
     */
    @ResponseBody
    @ExceptionHandler(AccountNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String employeeNotFoundHandler(AccountNotFoundException ex) {
        return ex.getMessage();
    }
}
