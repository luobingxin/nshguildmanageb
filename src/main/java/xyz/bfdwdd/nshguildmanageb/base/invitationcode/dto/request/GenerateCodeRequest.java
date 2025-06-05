package xyz.bfdwdd.nshguildmanageb.base.invitationcode.dto.request;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GenerateCodeRequest {
    private String creatorId; // 创建者ID
    private Long guildId;     // 关联帮会ID
    private Integer maxUses;  // 最大使用次数
    private Integer durationHours; // 有效小时数
}