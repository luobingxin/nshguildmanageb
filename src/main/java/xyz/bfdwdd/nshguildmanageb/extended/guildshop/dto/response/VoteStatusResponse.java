package xyz.bfdwdd.nshguildmanageb.extended.guildshop.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Schema(description = "帮会商城投票状态")
public class VoteStatusResponse {

    @Schema(description = "投票ID", example = "vote_001")
    private String voteId;

    @Schema(description = "操作类型", example = "ADD_PRODUCT")
    private String actionType;

    @Schema(description = "当前票数", example = "4")
    private Integer currentVotes;

    @Schema(description = "所需票数", example = "5")
    private Integer requiredVotes;

    @Schema(description = "投票状态", example = "PENDING, APPROVED, REJECTED")
    private String status;

    @Schema(description = "创建时间", example = "2025-04-05T10:00:00")
    private LocalDateTime createdAt;
}