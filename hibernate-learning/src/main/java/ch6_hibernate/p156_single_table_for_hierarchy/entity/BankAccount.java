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
@DiscriminatorValue("BA")
// @Table(name = "bank_accounts") - общая таблица определена в 'BillingDetails'
public class BankAccount extends BillingDetails {

    @NotNull
    private String account;
    @NotNull
    private String bankName;
    @NotNull
    private String swift;

}
