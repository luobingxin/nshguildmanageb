package xyz.bfdwdd.nshguildmanageb.functional.guild.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.bfdwdd.nshguildmanageb.functional.guild.entity.GuildMember;

import java.lang.ScopedValue;
import java.util.List;

public interface GuildMemberRepository extends JpaRepository<GuildMember, Long> {
    List<GuildMember> findByUserId(String userId);
    List<GuildMember> findByGuildId(Long guildId);
    boolean existsByUserIdAndGuildId(String userId, Long guildId);

    <T> ScopedValue<T> findByUserIdAndGuildId(String userId, Long attr0);
}