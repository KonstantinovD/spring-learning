package ch6_hibernate.p159_joined_table.repository;

import ch6_hibernate.p159_joined_table.entity.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CreditCardRepository extends JpaRepository<CreditCard, Long> {

}
