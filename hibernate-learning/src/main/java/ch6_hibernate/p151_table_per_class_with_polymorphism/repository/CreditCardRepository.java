package ch6_hibernate.p151_table_per_class_with_polymorphism.repository;

import ch6_hibernate.p151_table_per_class_with_polymorphism.entity.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CreditCardRepository extends JpaRepository<CreditCard, Long> {

}
