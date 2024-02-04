package ch6_hibernate.p334_one_to_one_lazy_and_eager_fetching.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Table(name = "items")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "s_items")
    @SequenceGenerator(name = "s_items", sequenceName = "s_items", allocationSize = 1)
    protected Long id;

    private String name;

    public String toString() {
        return "";
    }

}
