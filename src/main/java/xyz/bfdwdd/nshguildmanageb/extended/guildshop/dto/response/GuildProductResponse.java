package xyz.bfdwdd.nshguildmanageb.extended.guildshop.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "帮会商城商品信息")
public class GuildProductResponse {

    @Schema(description = "商品ID", example = "product_001")
    private String id;

    @Schema(description = "商品名称", example = "帮会徽章")
    private String name;

    @Schema(description = "商品描述", example = "本帮会专属纪念品")
    private String description;

    @Schema(description = "价格", example = "500")
    private Integer price;

    @Schema(description = "库存数量", example = "100")
    private Integer stock;

    @Schema(description = "货币类型", example = "MILITARY_MERIT (军功点), LOBI (洛币)")
    private String currencyType;

    @Schema(description = "所属帮会ID", example = "guild_001")
    private String guildId;
}