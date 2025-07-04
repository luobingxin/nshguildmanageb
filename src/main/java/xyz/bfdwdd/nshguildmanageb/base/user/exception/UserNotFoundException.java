package xyz.bfdwdd.nshguildmanageb.base.user.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends UserOperationException {
    public UserNotFoundException(String message) {
        super(message);
    }
}