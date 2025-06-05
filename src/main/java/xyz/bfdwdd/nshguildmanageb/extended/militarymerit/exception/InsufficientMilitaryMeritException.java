package xyz.bfdwdd.nshguildmanageb.extended.militarymerit.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InsufficientMilitaryMeritException extends RuntimeException {
    public InsufficientMilitaryMeritException(String message) {
        super(message);
    }
}