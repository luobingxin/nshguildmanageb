package xyz.bfdwdd.nshguildmanageb.functional.shop.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "购买商品请求")
public class PurchaseProductRequest {

    @Schema(description = "用户ID", example = "user123")
    private String userId;

    @Schema(description = "商品ID", example = "1")
    private Long productId;

    @Schema(description = "购买数量", example = "2")
    private Integer quantity;
}