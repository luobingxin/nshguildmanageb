package xyz.bfdwdd.nshguildmanageb.functional.guild.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class NotGuildOwnerException extends RuntimeException {
    public NotGuildOwnerException(String message) {
        super(message);
    }
}