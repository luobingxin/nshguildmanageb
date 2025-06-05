package xyz.bfdwdd.nshguildmanageb.extended.meritconversion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.bfdwdd.nshguildmanageb.extended.meritconversion.entity.ConversionRate;

import java.util.Optional;

public interface ConversionRateRepository extends JpaRepository<ConversionRate, String> {
    // 根据帮会ID查找转换率
    Optional<ConversionRate> findByGuildId(String guildId);
}