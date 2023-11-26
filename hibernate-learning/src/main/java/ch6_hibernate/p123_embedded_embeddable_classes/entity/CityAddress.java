package ch6_hibernate.p123_embedded_embeddable_classes.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Embeddable;


@Data
@Accessors(chain = true)
@Embeddable
public class CityAddress {
    @Column(nullable = false)
    private String countryName;
    private String cityName;

}
