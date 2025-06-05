package xyz.bfdwdd.nshguildmanageb.extended.guildshop.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "确认帮会商城操作投票请求")
public class ConfirmVoteRequest {

    @Schema(description = "投票ID", example = "vote_001")
    private String voteId;

    @Schema(description = "确认人ID", example = "guild_admin")
    private String confirmorId;

    @Schema(description = "是否批准", example = "true")
    private Boolean approved;
}