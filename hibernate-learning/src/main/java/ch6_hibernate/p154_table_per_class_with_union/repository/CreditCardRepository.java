package ch6_hibernate.p154_table_per_class_with_union.repository;

import ch6_hibernate.p154_table_per_class_with_union.entity.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CreditCardRepository extends JpaRepository<CreditCard, Long> {

}
