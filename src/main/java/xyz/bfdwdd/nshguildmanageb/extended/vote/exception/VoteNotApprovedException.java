package xyz.bfdwdd.nshguildmanageb.extended.vote.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class VoteNotApprovedException extends RuntimeException {
    public VoteNotApprovedException(String message) {
        super(message);
    }
}