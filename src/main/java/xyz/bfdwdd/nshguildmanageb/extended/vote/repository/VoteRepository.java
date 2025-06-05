package xyz.bfdwdd.nshguildmanageb.extended.vote.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xyz.bfdwdd.nshguildmanageb.extended.vote.entity.GuildVote;

import java.util.List;

@Repository
public interface VoteRepository extends JpaRepository<GuildVote, Long> {
    List<GuildVote> findByGuildId(Long guildId);
    List<GuildVote> findByStatus(String status);
}