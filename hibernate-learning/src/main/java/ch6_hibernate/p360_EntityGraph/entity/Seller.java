package ch6_hibernate.p360_EntityGraph.entity;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;


@Entity
@Data
@Accessors(chain = true)
@Table(name = "sellers")
public class Seller {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "s_sellers")
    @SequenceGenerator(name = "s_sellers", sequenceName = "s_sellers", allocationSize = 1)
    protected Long id;

    @Column(nullable = false)
    private String externalId;

    private String name;

    @OneToMany(mappedBy = "seller", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<Item> items = new ArrayList<>();

}
