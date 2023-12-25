package ch6_hibernate.p170_polymorphic_associations.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;


@Data
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class BillingDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "s_billing_details")
    @SequenceGenerator(name = "s_billing_details", sequenceName = "s_billing_details", allocationSize = 1)
    protected Long id;

    @NotNull
    protected String owner;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToOne
    protected User user;

}
