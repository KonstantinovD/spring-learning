package ch6_hibernate.p360_EntityGraph.entity;

import ch6_hibernate.p360_EntityGraph.entity.id.ItemId;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;
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

    @ManyToOne
    @JoinColumn(name = "seller_id", referencedColumnName = "id")
    private Seller seller;

}
