package ch6_hibernate.p279_1_insert_id_order.repository;

import ch6_hibernate.p279_1_insert_id_order.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;


@Repository
public interface AddressRepository extends JpaRepository<Address, BigDecimal> {

}
