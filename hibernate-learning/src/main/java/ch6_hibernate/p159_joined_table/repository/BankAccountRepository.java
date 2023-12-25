package ch6_hibernate.p159_joined_table.repository;

import ch6_hibernate.p159_joined_table.entity.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {

}
