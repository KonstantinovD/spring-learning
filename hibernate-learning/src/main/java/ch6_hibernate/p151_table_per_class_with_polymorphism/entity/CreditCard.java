package ch6_hibernate.p151_table_per_class_with_polymorphism.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Table(name = "credit_cards")
public class CreditCard extends BillingDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "s_credit_cards")
    @SequenceGenerator(name = "s_credit_cards", sequenceName = "s_credit_cards", allocationSize = 1)
    private Long id;
    @NotNull
    private String cardNumber;
    @NotNull
    private String expMonth;
    @NotNull
    private String expYear;
}
