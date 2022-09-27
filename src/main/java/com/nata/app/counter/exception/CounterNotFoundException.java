package com.nata.app.counter.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Counter not found")
public class CounterNotFoundException extends RuntimeException{

    public CounterNotFoundException() {
        super();
    }
}
