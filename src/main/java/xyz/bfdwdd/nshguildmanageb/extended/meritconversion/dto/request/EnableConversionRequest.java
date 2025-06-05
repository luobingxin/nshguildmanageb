package xyz.bfdwdd.nshguildmanageb.extended.meritconversion.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "启用或关闭军功点转换功能")
public class EnableConversionRequest {

    @Schema(description = "操作者ID", example = "guild_leader")
    private String operatorId;

    @Schema(description = "所属帮会ID", example = "guild_001")
    private String guildId;

    @Schema(description = "是否启用", example = "true")
    private Boolean enable;
}