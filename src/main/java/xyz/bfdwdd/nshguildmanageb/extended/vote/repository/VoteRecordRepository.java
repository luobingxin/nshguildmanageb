package xyz.bfdwdd.nshguildmanageb.extended.vote.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xyz.bfdwdd.nshguildmanageb.extended.vote.entity.GuildVoteRecord;

import java.util.List;

@Repository
public interface VoteRecordRepository extends JpaRepository<GuildVoteRecord, Long> {
    List<GuildVoteRecord> findByVoteId(Long voteId);
    List<GuildVoteRecord> findByVoterId(String voterId);

    boolean existsByVoteIdAndVoterId(Long voteId, String userId);

    int countByVoteIdAndApprovedTrue(Long voteId);
}