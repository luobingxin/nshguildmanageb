package xyz.bfdwdd.nshguildmanageb.extended.militarymerit.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class MeritNotFoundException extends RuntimeException {
    public MeritNotFoundException(String message) {
        super(message);
    }
}