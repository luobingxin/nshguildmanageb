package xyz.bfdwdd.nshguildmanageb.extended.guildshop.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "发起帮会商城操作投票请求")
public class InitiateVoteRequest {

    @Schema(description = "发起者ID", example = "guild_leader")
    private String initiatorId;

    @Schema(description = "操作类型", example = "ADD_PRODUCT, REMOVE_PRODUCT")
    private String actionType;

    @Schema(description = "关联对象ID（如商品ID）", example = "product_001")
    private String targetId;

    public String getGuildId() {
        return null;
    }
}