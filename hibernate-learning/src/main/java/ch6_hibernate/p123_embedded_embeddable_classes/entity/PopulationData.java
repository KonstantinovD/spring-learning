package ch6_hibernate.p123_embedded_embeddable_classes.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;


@Data
@Accessors(chain = true)
public class PopulationData {
    private Long humansNumber;
    private BigDecimal manPercentage;
    private String populationInfo;
}
