package ch6_hibernate.p361_SqlResultSetMapping.repository;

import ch6_hibernate.p361_SqlResultSetMapping.dto.ItemAttributeDto;
import ch6_hibernate.p361_SqlResultSetMapping.entity.ItemAttribute;
import ch6_hibernate.p361_SqlResultSetMapping.entity.id.ItemAttributeId;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.UUID;


public interface ItemAttributeRepository
        extends JpaRepository<ItemAttribute, ItemAttributeId> {

    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH,
            attributePaths = {"code", "value"})
    List<ItemAttribute> findAll();

    // невозможно установить аргумент типа enum - AttributeCategory
    @Query(nativeQuery = true, name = "itemAttributesMapping")
    List<ItemAttributeDto> findByItemIdInAndItemDate(
            Collection<UUID> itemIds,
            LocalDate itemDate,
            String category);

}
