package ch6_hibernate.p277_entity_proxy_reference.repository;

import ch6_hibernate.p277_entity_proxy_reference.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;


@Repository
public interface UserRepository extends JpaRepository<User, BigDecimal> {

}
