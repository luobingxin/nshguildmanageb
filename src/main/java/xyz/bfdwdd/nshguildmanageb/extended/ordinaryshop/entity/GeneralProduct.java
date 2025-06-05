package xyz.bfdwdd.nshguildmanageb.extended.ordinaryshop.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "general_product")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GeneralProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "price", nullable = false)
    private Integer price;

    @Column(name = "stock", nullable = false)
    private Integer stock;

    @Column(name = "currency_type", nullable = false)
    private String currencyType; // LOBI, MILITARY_MERIT, POINTS 等
}