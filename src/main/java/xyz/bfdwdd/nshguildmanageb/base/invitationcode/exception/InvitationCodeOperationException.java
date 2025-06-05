package xyz.bfdwdd.nshguildmanageb.base.invitationcode.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public abstract class InvitationCodeOperationException extends RuntimeException {
    public InvitationCodeOperationException(String message) {
        super(message);
    }
}