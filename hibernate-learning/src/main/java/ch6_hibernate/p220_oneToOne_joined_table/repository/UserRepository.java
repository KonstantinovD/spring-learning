package ch6_hibernate.p220_oneToOne_joined_table.repository;

import ch6_hibernate.p220_oneToOne_joined_table.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;


@Repository
public interface UserRepository extends JpaRepository<User, BigDecimal> {

}
