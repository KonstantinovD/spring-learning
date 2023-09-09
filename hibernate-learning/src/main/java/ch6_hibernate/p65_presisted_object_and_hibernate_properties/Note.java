package ch6_hibernate.p65_presisted_object_and_hibernate_properties;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.math.BigDecimal;


@NoArgsConstructor
@Entity
@Table(name = "notes")
@Getter
@Setter
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "s_notes")
    @SequenceGenerator(name = "s_notes", sequenceName = "s_notes", allocationSize = 1)
    private BigDecimal id;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToOne
    private User user;

    private String text;

}
