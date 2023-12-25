package ch6_hibernate.p156_single_table_for_hierarchy.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;


@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@DiscriminatorValue("CC")
// @Table(name = "credit_cards") - общая таблица определена в 'BillingDetails'
public class CreditCard extends BillingDetails {

    @NotNull
    private String cardNumber;
    @NotNull
    private String expMonth;
    @NotNull
    private String expYear;
}
