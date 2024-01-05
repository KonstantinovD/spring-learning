package ch6_hibernate.p279_1_insert_id_order.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;


@Data
@Entity
@Table(name = "addresses")
@Accessors(chain = true)
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    private BigDecimal id;

    @Column(nullable = false)
    private String countryName;

    private String cityName;

    @ManyToOne
    protected User user;

}