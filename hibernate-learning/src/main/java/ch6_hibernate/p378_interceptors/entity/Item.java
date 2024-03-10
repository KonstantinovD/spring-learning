package ch6_hibernate.p378_interceptors.entity;

import ch6_hibernate.p378_interceptors.entity.id.ItemId;
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
import java.time.LocalDateTime;
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

    private UUID twiceId;

    private String name;

    private String code;

    private BigDecimal amount;

    private int intAmount1;

    private int intAmount2;

    private Integer intAmount3;

    private Integer intAmount4;

    private long longAmount1;

    private long longAmount2;

    private Long longAmount3;

    private Long longAmount4;

    private LocalDate dateValue;

    private LocalDateTime timeValue;

    @ManyToOne
    @JoinColumn(name = "seller_id", referencedColumnName = "id")
    private Seller seller;

}
