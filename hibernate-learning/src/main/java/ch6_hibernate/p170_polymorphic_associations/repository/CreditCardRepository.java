package ch6_hibernate.p170_polymorphic_associations.repository;

import ch6_hibernate.p170_polymorphic_associations.entity.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CreditCardRepository extends JpaRepository<CreditCard, Long> {

}
