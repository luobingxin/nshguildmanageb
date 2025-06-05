package xyz.bfdwdd.nshguildmanageb.extended.ordinaryshop.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Schema(description = "商品审核记录")
public class ReviewResponse {

    @Schema(description = "审核ID", example = "1001")
    private Long id;

    @Schema(description = "商品ID", example = "1")
    private Long productId;

    @Schema(description = "审核人ID", example = "sysadmin")
    private String reviewerId;

    @Schema(description = "审核状态", example = "APPROVED, REJECTED")
    private String status;

    @Schema(description = "审核时间", example = "2025-04-05T10:00:00")
    private LocalDateTime reviewTime;
}