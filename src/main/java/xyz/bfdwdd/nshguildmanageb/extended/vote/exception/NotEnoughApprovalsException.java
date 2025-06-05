package xyz.bfdwdd.nshguildmanageb.extended.vote.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NotEnoughApprovalsException extends RuntimeException {
    public NotEnoughApprovalsException(String message) {
        super(message);
    }
}