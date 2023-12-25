package ch6_hibernate.p156_single_table_for_hierarchy.repository;

import ch6_hibernate.p156_single_table_for_hierarchy.entity.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CreditCardRepository extends JpaRepository<CreditCard, Long> {

}
