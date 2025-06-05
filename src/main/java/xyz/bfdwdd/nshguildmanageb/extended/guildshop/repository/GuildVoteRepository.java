package xyz.bfdwdd.nshguildmanageb.extended.guildshop.repository;

import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xyz.bfdwdd.nshguildmanageb.extended.guildshop.entity.GuildVote;

import java.util.Optional;

@Repository
@Primary
public interface GuildVoteRepository extends JpaRepository<GuildVote, String> {
    Optional<GuildVote> findById(String id);
}