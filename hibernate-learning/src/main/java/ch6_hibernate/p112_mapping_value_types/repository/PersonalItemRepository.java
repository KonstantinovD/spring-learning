package ch6_hibernate.p112_mapping_value_types.repository;

import ch6_hibernate.p112_mapping_value_types.PersonalItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface PersonalItemRepository extends JpaRepository<PersonalItem, BigDecimal> {

}
