package ch6_hibernate.p151_table_per_class_with_polymorphism.repository;

import ch6_hibernate.p151_table_per_class_with_polymorphism.entity.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {

}
