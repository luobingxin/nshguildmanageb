package xyz.bfdwdd.nshguildmanageb.functional.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.bfdwdd.nshguildmanageb.functional.shop.entity.PurchaseRecord;

import java.util.List;

public interface PurchaseRecordRepository extends JpaRepository<PurchaseRecord, Long> {
    List<PurchaseRecord> findByUserId(String userId);
}