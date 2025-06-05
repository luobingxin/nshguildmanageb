package xyz.bfdwdd.nshguildmanageb.base.log.dto.response;

import lombok.*;
import xyz.bfdwdd.nshguildmanageb.base.log.entity.Log;

import java.time.format.DateTimeFormatter;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LogResponse {
    private Long id;
    private String action;
    private String userId;
    private Long guildId;
    private String ipAddress;
    private String description;
    private String timestamp;

    public static LogResponse fromEntity(Log log) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        return new LogResponse(
                log.getId(),
                log.getAction(),
                log.getUserId(),
                log.getGuildId(),
                log.getIpAddress(),
                log.getDescription(),
                log.getTimestamp().format(formatter)
        );
    }
}