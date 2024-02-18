package ch6_hibernate.p347_n_plus_1_problem.repository;

import ch6_hibernate.p347_n_plus_1_problem.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

}
