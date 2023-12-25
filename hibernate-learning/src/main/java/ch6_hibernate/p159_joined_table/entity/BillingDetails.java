package ch6_hibernate.p159_joined_table.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


@Data
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "billing_details")
public class BillingDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "s_billing_details")
    @SequenceGenerator(name = "s_billing_details", sequenceName = "s_billing_details", allocationSize = 1)
    protected Long id;

    @NotNull
    protected String owner;

}
