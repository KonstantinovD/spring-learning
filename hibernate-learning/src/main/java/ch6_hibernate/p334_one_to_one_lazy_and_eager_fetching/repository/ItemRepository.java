package ch6_hibernate.p334_one_to_one_lazy_and_eager_fetching.repository;

import ch6_hibernate.p334_one_to_one_lazy_and_eager_fetching.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

}
