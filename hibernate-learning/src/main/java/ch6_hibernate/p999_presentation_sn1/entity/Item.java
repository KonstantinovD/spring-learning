package ch6_hibernate.p999_presentation_sn1.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.UUID;


@Entity
@Getter
@Setter
@ToString
@Accessors(chain = true)
@Table(name = "items")
public class Item {

    @Id// стартуем новую транзакцию
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "s_items")
    @SequenceGenerator(name = "s_items", sequenceName = "s_items", allocationSize = 1)
    protected Long id;

    private String name;
    private UUID externalId;

}
