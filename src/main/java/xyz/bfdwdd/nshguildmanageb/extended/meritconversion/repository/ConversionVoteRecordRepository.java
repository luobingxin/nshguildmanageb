package xyz.bfdwdd.nshguildmanageb.extended.meritconversion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.bfdwdd.nshguildmanageb.extended.meritconversion.entity.ConversionVoteRecord;

import java.util.List;

public interface ConversionVoteRecordRepository extends JpaRepository<ConversionVoteRecord, Long> {
    List<ConversionVoteRecord> findByVoteId(String voteId);
}