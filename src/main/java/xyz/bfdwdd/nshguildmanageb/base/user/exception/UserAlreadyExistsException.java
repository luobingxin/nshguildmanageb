package xyz.bfdwdd.nshguildmanageb.base.user.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class UserAlreadyExistsException extends UserOperationException {
    public UserAlreadyExistsException(String message) {
        super(message);
    }
}