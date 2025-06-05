package xyz.bfdwdd.nshguildmanageb.extended.guildshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.bfdwdd.nshguildmanageb.extended.guildshop.entity.GuildProduct;

import java.util.List;

public interface GuildProductRepository extends JpaRepository<GuildProduct, String> {
    List<GuildProduct> findByGuildId(String guildId);
}