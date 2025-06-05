package xyz.bfdwdd.nshguildmanageb.functional.guild.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class GuildNotFoundException extends RuntimeException {
    public GuildNotFoundException(String message) {
        super(message);
    }
}