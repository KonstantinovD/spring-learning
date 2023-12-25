package ch6_hibernate.p156_single_table_for_hierarchy.repository;

import ch6_hibernate.p156_single_table_for_hierarchy.entity.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {

}
