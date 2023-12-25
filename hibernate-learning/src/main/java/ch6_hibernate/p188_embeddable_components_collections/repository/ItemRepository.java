package ch6_hibernate.p188_embeddable_components_collections.repository;

import ch6_hibernate.p188_embeddable_components_collections.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ItemRepository extends JpaRepository<Item, Long> {

}
