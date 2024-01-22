package ch6_hibernate.p288_SessionFlushing.repository;

import ch6_hibernate.p288_SessionFlushing.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<User, BigDecimal> {

    @Query("select count(*) from User u")
    long countUsers();

    List<User> findByAgeGreaterThan(int age);
}
