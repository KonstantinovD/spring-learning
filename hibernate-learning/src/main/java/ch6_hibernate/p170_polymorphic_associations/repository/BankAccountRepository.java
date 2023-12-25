package ch6_hibernate.p170_polymorphic_associations.repository;

import ch6_hibernate.p170_polymorphic_associations.entity.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {

}
