package xyz.bfdwdd.nshguildmanageb.extended.guildshop.repository;

import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xyz.bfdwdd.nshguildmanageb.extended.guildshop.entity.GuildVoteRecord;

import java.util.List;

@Primary
@Repository
public interface GuildVoteRecordRepository extends JpaRepository<GuildVoteRecord, Long> {
    List<GuildVoteRecord> findByVoteId(String voteId);
}