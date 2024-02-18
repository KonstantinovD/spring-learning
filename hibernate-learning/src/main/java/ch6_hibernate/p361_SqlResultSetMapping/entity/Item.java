package ch6_hibernate.p361_SqlResultSetMapping.entity;

import ch6_hibernate.p361_SqlResultSetMapping.entity.id.ItemId;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Entity
@Getter
@Setter
@ToString
@Accessors(chain = true)
@IdClass(ItemId.class)
@Table(name = "items")
public class Item {

    @Id
    @Column(nullable = false, name = "external_id")
    private UUID externalId;

    @Id
    @Column(nullable = false, name = "item_date")
    private LocalDate itemDate;

    private String name;

    private String code;

    private BigDecimal amount;

    @OneToMany(
            mappedBy = "item",
            cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<ItemAttribute> attrs = new ArrayList<>();

}
