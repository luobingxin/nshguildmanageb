package xyz.bfdwdd.nshguildmanageb.extended.guildshop.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "guild_product")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GuildProduct {

    @Id
    private String id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "price", nullable = false)
    private Integer price;

    @Column(name = "stock", nullable = false)
    private Integer stock;

    @Column(name = "currency_type", nullable = false)
    private String currencyType; // MILITARY_MERIT, LOBI, POINTS 等

    @Column(name = "guild_id", nullable = false)
    private String guildId;
}