package xyz.bfdwdd.nshguildmanageb.base.notification.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotificationNotFoundException extends NotificationOperationException {
    public NotificationNotFoundException(String message) {
        super(message);
    }
}