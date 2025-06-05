package xyz.bfdwdd.nshguildmanageb.base.invitationcode.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.bfdwdd.nshguildmanageb.base.invitationcode.entity.InvitationCode;

import java.util.Optional;

public interface InvitationCodeRepository extends JpaRepository<InvitationCode, String> {
    Optional<InvitationCode> findByCode(String code);
    Optional<InvitationCode> findByQueryCode(String queryCode);
}