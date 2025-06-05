package xyz.bfdwdd.nshguildmanageb.base.log.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public abstract class LogOperationException extends RuntimeException {
    public LogOperationException(String message) {
        super(message);
    }
}