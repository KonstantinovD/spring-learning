package ch6_hibernate.p159_joined_table.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Table(name = "bank_accounts")
public class BankAccount extends BillingDetails {

    @NotNull
    private String account;
    @NotNull
    private String bankName;
    @NotNull
    private String swift;

}
