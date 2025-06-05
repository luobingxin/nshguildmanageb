package xyz.bfdwdd.nshguildmanageb.extended.meritconversion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.bfdwdd.nshguildmanageb.extended.meritconversion.entity.ConversionVote;

import java.util.Optional;

public interface ConversionVoteRepository extends JpaRepository<ConversionVote, String> {
    Optional<ConversionVote> findById(String id);
}