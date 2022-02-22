package com.tap.travelfareservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class ServerInternalException extends RuntimeException{
    public ServerInternalException(String msg) {
        super(msg);
    }
}
