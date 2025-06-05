package xyz.bfdwdd.nshguildmanageb.extended.militarymerit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.bfdwdd.nshguildmanageb.extended.militarymerit.entity.MilitaryMerit;

import java.util.List;
import java.util.Optional;

public interface MilitaryMeritRepository extends JpaRepository<MilitaryMerit, Long> {
    Optional<MilitaryMerit> findByUserIdAndGuildId(String userId, Long guildId);
    List<MilitaryMerit> findByUserId(String userId);
    List<MilitaryMerit> findByGuildId(Long guildId);
}