package xyz.bfdwdd.nshguildmanageb.base.user.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public abstract class UserOperationException extends RuntimeException {
    public UserOperationException(String message) {
        super(message);
    }
}