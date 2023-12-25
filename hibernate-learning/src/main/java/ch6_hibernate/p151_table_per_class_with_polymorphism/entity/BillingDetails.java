package ch6_hibernate.p151_table_per_class_with_polymorphism.entity;

import lombok.Data;

import javax.persistence.MappedSuperclass;

@Data
// An entity cannot be annotated with both @Entity and @MappedSuperclass
// первая стратегия - не использовать @Inheritance вообще - суперкласс не является Entity
@MappedSuperclass
public abstract class BillingDetails {

    protected String owner;

}
