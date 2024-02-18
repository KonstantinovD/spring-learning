package ch6_hibernate.p361_SqlResultSetMapping.entity;

import ch6_hibernate.p361_SqlResultSetMapping.dictionary.AttributeCategory;
import ch6_hibernate.p361_SqlResultSetMapping.dto.ItemAttributeDto;
import ch6_hibernate.p361_SqlResultSetMapping.entity.id.ItemAttributeId;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.UUID;

import static javax.persistence.EnumType.STRING;

// невозможно в category установить поле типа enum - AttributeCategory
@SqlResultSetMapping(
        name = "itemAttributesDtoMapping",
        classes = @ConstructorResult(
                targetClass = ItemAttributeDto.class,
                columns = {
                        @ColumnResult(name = "item_id", type = UUID.class),
                        @ColumnResult(name = "item_date", type = LocalDate.class),
                        @ColumnResult(name = "code", type = String.class),
                        @ColumnResult(name = "value", type = String.class),
                        @ColumnResult(name = "category", type = String.class)
                }
        )
)
@NamedNativeQuery(
        name = "itemAttributesMapping",
        query =
                """
                select code, item_id, item_date, value, category from item_attributes 
                where item_id in :itemIds and item_date = :itemDate and category = :category
                """,
        resultClass = ItemAttributeDto.class,
        resultSetMapping = "itemAttributesDtoMapping"
)
@Entity
@Getter
@Setter
@ToString
@Accessors(chain = true)
@IdClass(ItemAttributeId.class)
@Table(name = "item_attributes")
public class ItemAttribute {

    @Id
    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "item_id", referencedColumnName = "external_id"),
            @JoinColumn(name = "item_date", referencedColumnName = "item_date")
    })
    private Item item;

    @Id
    @Column(nullable = false)
    private String code;

    @Column
    private String value;

    @Column(nullable = false)
    @Enumerated(STRING)
    private AttributeCategory category;

}
