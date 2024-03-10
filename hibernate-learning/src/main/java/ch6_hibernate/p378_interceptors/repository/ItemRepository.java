package ch6_hibernate.p378_interceptors.repository;

import ch6_hibernate.p378_interceptors.entity.Item;
import ch6_hibernate.p378_interceptors.entity.id.ItemId;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ItemRepository extends JpaRepository<Item, ItemId> {

    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH,
            attributePaths = {"externalId", "amount"})
    List<Item> findAll();

}
