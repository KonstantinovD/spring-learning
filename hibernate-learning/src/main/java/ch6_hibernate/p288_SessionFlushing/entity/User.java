package ch6_hibernate.p288_SessionFlushing.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.math.BigDecimal;


@Data
@Entity
@Table(name = "users")
@Accessors(chain = true)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "s_users")
    @SequenceGenerator(name = "s_users", sequenceName = "s_users", allocationSize = 1)
    @EqualsAndHashCode.Exclude
    private BigDecimal id;

    private String name;

    private int age;
}
