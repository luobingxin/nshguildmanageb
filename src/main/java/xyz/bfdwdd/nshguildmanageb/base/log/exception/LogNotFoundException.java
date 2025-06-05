package xyz.bfdwdd.nshguildmanageb.base.log.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class LogNotFoundException extends LogOperationException {
    public LogNotFoundException(String message) {
        super(message);
    }
}