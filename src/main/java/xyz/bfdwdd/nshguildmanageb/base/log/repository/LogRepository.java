package xyz.bfdwdd.nshguildmanageb.base.log.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.bfdwdd.nshguildmanageb.base.log.entity.Log;

import java.util.List;

public interface LogRepository extends JpaRepository<Log, Long> {
    List<Log> findByUserId(String userId);
    List<Log> findByGuildId(Long guildId);
    List<Log> findByAction(String action);
}