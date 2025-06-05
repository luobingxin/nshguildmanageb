package xyz.bfdwdd.nshguildmanageb.extended.militarymerit.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "用户所有军功点总览")
public class MeritSummaryResponse {

    @Schema(description = "用户ID", example = "user_123")
    private String userId;

    @Schema(description = "各帮会军功点汇总", example = "{\"guild_1\": 150, \"guild_2\": 200}")
    private Map<Long, Integer> guildMerits;

    @Schema(description = "总军功点数", example = "350")
    private Integer totalMeritPoints;
}