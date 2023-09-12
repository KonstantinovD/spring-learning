package ch6_hibernate.sn001_inner_transaction.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name = "test_table")
@Getter
@Setter
@ToString
public class TestNote {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "s_test_table")
    @SequenceGenerator(name = "s_test_table", sequenceName = "s_test_table", allocationSize = 1)
    private Long id;

    @Column(nullable = false)
    // @Size not working
    // @Size(max = 30, message = "Property \"name\" can not contain more than {max} characters")
    private String name;

    @Column
    // @Size(max = 255, message = "Property \"text\" can not contain more than {max} characters")
    private String text;
}
