package ch6_hibernate.p75_bean_hibernate_validator;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.validation.annotation.Validated;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;

import static ch6_hibernate.p75_bean_hibernate_validator.constants.HibernateValidationExampleConstants.AUCTION_END_CONSTRAINT_VALIDATION_MESSAGE;
import static ch6_hibernate.p75_bean_hibernate_validator.constants.HibernateValidationExampleConstants.NAME_CONSTRAINT_VALIDATION_MESSAGE;


@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@Table(name = "items")
@Validated
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "s_items")
    @SequenceGenerator(name = "s_items", sequenceName = "s_items", allocationSize = 1)
    private BigDecimal id;

    @NotNull(message = "Name should be not null.")
    @Size(
            min = 2,
            max = 255,
            message = NAME_CONSTRAINT_VALIDATION_MESSAGE
    )
    private String name;

    @Future( // игнорирует null, что очевидно
            message = AUCTION_END_CONSTRAINT_VALIDATION_MESSAGE)
    private LocalDate auctionEnd;
}
