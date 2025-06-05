package xyz.bfdwdd.nshguildmanageb.extended.guildshop.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "购买帮会商城商品请求")
public class BuyGuildProductRequest {

    @Schema(description = "用户ID", example = "user123")
    private String userId;

    @Schema(description = "商品ID", example = "product_001")
    private String productId;

    @Schema(description = "购买数量", example = "2")
    private Integer quantity;
}