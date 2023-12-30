package ch6_hibernate.p220_oneToOne_joined_table.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
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

    @NotNull
    private String name;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToOne(fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_address", // Имя таблицы соединения - обязательно!
            joinColumns = @JoinColumn(name = "user_id"), // столбец id текущей таблицы (users)
            // столбец id таблицы, к которой идёт связь (addresses)
            // unique = true и nullable=false в @JoinColumn работает только при создании схемы БД самим Hibernate
            inverseJoinColumns = @JoinColumn(name = "address_id", nullable = false, unique = true)
    )
    protected Address address;
}
