package ch6_hibernate.sn002_spring_cache.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TestNote {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "s_test_table")
    @SequenceGenerator(name = "s_test_table", sequenceName = "s_test_table", allocationSize = 1)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column
    private String text;
}
