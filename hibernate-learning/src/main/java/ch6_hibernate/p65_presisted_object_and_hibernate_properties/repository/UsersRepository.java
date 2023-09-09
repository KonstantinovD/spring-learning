package ch6_hibernate.p65_presisted_object_and_hibernate_properties.repository;

import ch6_hibernate.p65_presisted_object_and_hibernate_properties.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface UsersRepository extends JpaRepository<User, BigDecimal> {

}
