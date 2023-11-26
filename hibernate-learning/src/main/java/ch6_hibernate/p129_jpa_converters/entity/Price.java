package ch6_hibernate.p129_jpa_converters.entity;

import ch6_hibernate.p129_jpa_converters.converter.MonetaryAmountConverter;
import ch6_hibernate.p129_jpa_converters.dto.MonetaryAmount;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


@Entity
@Data
@Table(name = "prices")
@Accessors(chain = true)
public class Price {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "s_prices")
    @SequenceGenerator(name = "s_prices", sequenceName = "s_prices", allocationSize = 1)
    private Long id;

    @NotNull
    @Convert( // Необязательная аннотация, т. к. у конвертера активен параметр autoApply
            converter = MonetaryAmountConverter.class,
            disableConversion = false)
    @Column(nullable = false)
    private MonetaryAmount price;

    @NotNull
    @Column(nullable = false)
    private String itemId;

}
