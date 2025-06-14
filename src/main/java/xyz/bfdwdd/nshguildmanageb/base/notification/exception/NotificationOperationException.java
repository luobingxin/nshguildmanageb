package xyz.bfdwdd.nshguildmanageb.base.notification.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public abstract class NotificationOperationException extends RuntimeException {
    public NotificationOperationException(String message) {
        super(message);
    }
}