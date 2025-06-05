package xyz.bfdwdd.nshguildmanageb.extended.meritconversion.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "conversion_rate")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConversionRate {

    @Id
    private String id; // 帮会ID + 转换类型组成复合主键

    @Column(name = "merit_to_lobi_rate", nullable = false)
    private Double meritToLobiRate;

    @Column(name = "incoming_rate", nullable = false)
    private Double incomingRate;

    @Column(name = "outgoing_rate", nullable = false)
    private Double outgoingRate;

    @Column(name = "enabled", nullable = false)
    private Boolean enabled;

    private String guildId;
}