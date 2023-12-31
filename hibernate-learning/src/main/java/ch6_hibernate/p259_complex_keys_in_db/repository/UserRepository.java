package ch6_hibernate.p259_complex_keys_in_db.repository;

import ch6_hibernate.p259_complex_keys_in_db.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, User.UserId> {

}
