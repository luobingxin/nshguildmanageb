package xyz.bfdwdd.nshguildmanageb.extended.meritconversion.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "conversion_vote_record")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConversionVoteRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "vote_id", nullable = false)
    private String voteId;

    @Column(name = "voter_id", nullable = false)
    private String voterId;

    @Column(name = "approved", nullable = false)
    private Boolean approved;

    @Column(name = "vote_time", nullable = false)
    private LocalDateTime voteTime;
}