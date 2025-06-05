package xyz.bfdwdd.nshguildmanageb.extended.meritconversion.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "当前军功点转换率信息")
public class ConversionRateResponse {

    @Schema(description = "帮会ID", example = "guild_001")
    private String guildId;

    @Schema(description = "军功点转洛币汇率", example = "0.9")
    private Double meritToLobiRate;

    @Schema(description = "军功点转入率", example = "0.8")
    private Double incomingRate;

    @Schema(description = "军功点转出率", example = "0.7")
    private Double outgoingRate;

    @Schema(description = "是否启用", example = "true")
    private Boolean enabled;
}