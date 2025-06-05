package xyz.bfdwdd.nshguildmanageb.extended.militarymerit.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "军功点详情")
public class MeritDetailResponse {

    @Schema(description = "用户ID", example = "user_123")
    private String userId;

    @Schema(description = "帮会ID", example = "guild_456")
    private Long guildId;

    @Schema(description = "当前军功点数", example = "150")
    private Integer currentMeritPoints;

    @Schema(description = "最后更新时间", example = "2025-04-05T12:34:56")
    private LocalDateTime lastUpdated;
}