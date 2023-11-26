package ch6_hibernate.p129_jpa_converters.repository;

import ch6_hibernate.p129_jpa_converters.entity.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PriceRepository extends JpaRepository<Price, Long> {

}
