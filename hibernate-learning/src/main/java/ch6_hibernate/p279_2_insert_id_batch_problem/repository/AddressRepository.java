package ch6_hibernate.p279_2_insert_id_batch_problem.repository;

import ch6_hibernate.p279_2_insert_id_batch_problem.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;


@Repository
public interface AddressRepository extends JpaRepository<Address, BigDecimal> {

}
