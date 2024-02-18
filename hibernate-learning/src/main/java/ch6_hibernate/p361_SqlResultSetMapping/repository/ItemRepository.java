package ch6_hibernate.p361_SqlResultSetMapping.repository;

import ch6_hibernate.p361_SqlResultSetMapping.entity.Item;
import ch6_hibernate.p361_SqlResultSetMapping.entity.id.ItemId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ItemRepository extends JpaRepository<Item, ItemId> {

}
