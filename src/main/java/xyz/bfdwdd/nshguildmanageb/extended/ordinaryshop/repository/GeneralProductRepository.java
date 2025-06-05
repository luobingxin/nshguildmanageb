package xyz.bfdwdd.nshguildmanageb.extended.ordinaryshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.bfdwdd.nshguildmanageb.extended.ordinaryshop.entity.GeneralProduct;

import java.util.Optional;

public interface GeneralProductRepository extends JpaRepository<GeneralProduct, Long> {
    Optional<GeneralProduct> findByName(String name);
}