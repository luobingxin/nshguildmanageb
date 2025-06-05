package xyz.bfdwdd.nshguildmanageb.functional.guild.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class MemberNotInGuildException extends RuntimeException {
    public MemberNotInGuildException(String message) {
        super(message);
    }
}