package ch6_hibernate.p999_presentation_sn1.repository;

import ch6_hibernate.p999_presentation_sn1.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    Item findByName(String name);

}
