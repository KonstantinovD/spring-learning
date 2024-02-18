package ch6_hibernate.p351_batch_size_np1_solution.repository;

import ch6_hibernate.p351_batch_size_np1_solution.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

}
