package ch6_hibernate.p999_presentation_sn1.entity;

import ch6_hibernate.p999_presentation_sn1.dictionary.PaymentStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.math.BigDecimal;

import static ch6_hibernate.p999_presentation_sn1.dictionary.PaymentStatus.NEW;
import static javax.persistence.EnumType.STRING;


@Entity
@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@Table(name = "payments")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "s_payments")
    @SequenceGenerator(name = "s_payments", sequenceName = "s_payments", allocationSize = 1)
    private Long id;
    private String accountNumber;
    private BigDecimal amount;
    @Enumerated(STRING)
    private PaymentStatus status = NEW;
}