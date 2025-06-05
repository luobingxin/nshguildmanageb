package xyz.bfdwdd.nshguildmanageb.extended.meritconversion.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "设置军功点转换率请求")
public class SetConversionRateRequest {

    @Schema(description = "操作者ID", example = "sysadmin")
    private String operatorId;

    @Schema(description = "所属帮会ID", example = "guild_001")
    private String guildId;

    @Schema(description = "转入率", example = "0.8")
    private Double incomingRate;

    @Schema(description = "转出率", example = "0.7")
    private Double outgoingRate;

    public Double getMeritToLobiRate() {
        return null;
    }
}