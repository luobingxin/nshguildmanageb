package xyz.bfdwdd.nshguildmanageb.base.invitationcode.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.GONE)
public class InvitationCodeExpiredException extends InvitationCodeOperationException {
    public InvitationCodeExpiredException(String message) {
        super(message);
    }
}