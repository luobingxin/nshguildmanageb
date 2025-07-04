package xyz.bfdwdd.nshguildmanageb.base.permission.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class PermissionOperationException extends RuntimeException {
    public PermissionOperationException(String message) {
        super(message);
    }
}