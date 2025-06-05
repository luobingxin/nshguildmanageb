package xyz.bfdwdd.nshguildmanageb.extended.ordinaryshop.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "product_review")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductReview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_id", nullable = false)
    private Long productId;

    @Column(name = "reviewer_id", nullable = false)
    private String reviewerId;

    @Column(name = "status", nullable = false)
    private String status; // APPROVED, REJECTED

    @Column(name = "review_time", nullable = false)
    private LocalDateTime reviewTime;
}