package ch6_hibernate.sn001_inner_transaction.repository;

import ch6_hibernate.sn001_inner_transaction.entity.TestNote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;

import static org.hibernate.LockOptions.SKIP_LOCKED;


@Repository
public interface TestNoteRepository extends JpaRepository<TestNote, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @QueryHints({ @QueryHint(
            name = "javax.persistence.lock.timeout",
            value = SKIP_LOCKED + "") })
    @Transactional(propagation = Propagation.MANDATORY)
    TestNote findByName(String name);
}
