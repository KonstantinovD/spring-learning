package ch6_hibernate.p129_jpa_converters.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Currency;


@Data
@AllArgsConstructor
public class MonetaryAmount implements Serializable {

    protected final BigDecimal value;
    protected final Currency currency;

    public String toString() {
        return getValue() + " " + getCurrency();
    }

    public static MonetaryAmount fromString(String s) {
        String[] split = s.split(" ");
        return new MonetaryAmount(
                new BigDecimal(split[0]),
                Currency.getInstance(split[1]));
    }

}
