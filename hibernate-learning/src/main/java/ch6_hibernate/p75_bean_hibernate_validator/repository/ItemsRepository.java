package ch6_hibernate.p75_bean_hibernate_validator.repository;

import ch6_hibernate.p75_bean_hibernate_validator.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface ItemsRepository extends JpaRepository<Item, BigDecimal> {
}
