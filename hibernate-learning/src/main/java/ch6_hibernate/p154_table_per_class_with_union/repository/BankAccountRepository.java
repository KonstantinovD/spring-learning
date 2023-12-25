package ch6_hibernate.p154_table_per_class_with_union.repository;

import ch6_hibernate.p154_table_per_class_with_union.entity.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {

}
