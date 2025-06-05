package xyz.bfdwdd.nshguildmanageb.functional.shop.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "创建商品请求")
public class CreateProductRequest {

    @Schema(description = "商品名称", example = "洛书")
    private String name;

    @Schema(description = "商品描述", example = "逆水寒世界中的神秘宝物")
    private String description;

    @Schema(description = "价格", example = "100")
    private Integer price;

    @Schema(description = "库存数量", example = "50")
    private Integer stock;
}