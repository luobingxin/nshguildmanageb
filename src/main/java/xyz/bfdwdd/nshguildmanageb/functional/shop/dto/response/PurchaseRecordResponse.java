package xyz.bfdwdd.nshguildmanageb.functional.shop.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Schema(description = "购买记录响应信息")
public class PurchaseRecordResponse {

    @Schema(description = "购买记录ID", example = "1001")
    private Long id;

    @Schema(description = "用户ID", example = "user123")
    private String userId;

    @Schema(description = "商品ID", example = "1")
    private Long productId;

    @Schema(description = "购买数量", example = "2")
    private Integer quantity;

    @Schema(description = "购买时间", example = "2025-04-05T10:00:00")
    private LocalDateTime purchaseTime;
}