package xyz.bfdwdd.nshguildmanageb.base.log.dto.request;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LogSearchRequest {
    private String userId;
    private Long guildId;
    private String action;
    private String keyword;
}