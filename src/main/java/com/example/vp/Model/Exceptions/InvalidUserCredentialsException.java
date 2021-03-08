package com.example.vp.Model.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class InvalidUserCredentialsException extends RuntimeException{
    public InvalidUserCredentialsException() {
        super("You have entered invalid credentials");
    }
}
