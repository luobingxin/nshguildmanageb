package xyz.bfdwdd.nshguildmanageb.extended.militarymerit.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "清空军功点请求")
public class ClearMeritRequest {

    @Schema(description = "用户ID", example = "user_123")
    private String userId;

    @Schema(description = "帮会ID", example = "guild_456")
    private Long guildId;
}