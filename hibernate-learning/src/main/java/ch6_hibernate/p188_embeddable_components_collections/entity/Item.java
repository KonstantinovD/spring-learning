package ch6_hibernate.p188_embeddable_components_collections.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;


@Data
@Entity
@Table(name = "items")
@Accessors(chain = true)
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "s_items")
    @SequenceGenerator(name = "s_items", sequenceName = "s_items", allocationSize = 1)
    @EqualsAndHashCode.Exclude
    private BigDecimal id;

    @NotNull
    private String name;

    // means that the collection is not a collection of entities,
    // but a collection of simple types (Strings, etc.) or a collection of @Embeddable elements
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "images")
    protected Collection<Image> images = new ArrayList<>();

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name="properties", joinColumns = @JoinColumn(name = "item_id"))
    @Column(name = "property")
    protected Set<String> properties = new HashSet<>();

}