package ch6_hibernate.p151_table_per_class_with_polymorphism.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
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
@Table(name = "bank_accounts")
@AttributeOverride(
        name = "owner",
        column = @Column(name = "BA_OWNER", nullable = false))
public class BankAccount extends BillingDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "s_bank_accounts")
    @SequenceGenerator(name = "s_bank_accounts", sequenceName = "s_bank_accounts", allocationSize = 1)
    private Long id;
    @NotNull
    private String account;
    @NotNull
    private String bankName;
    @NotNull
    private String swift;

}
