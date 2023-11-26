package ch6_hibernate.p123_embedded_embeddable_classes.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Data
@Table(name = "cities")
@Accessors(chain = true)
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "s_cities")
    @SequenceGenerator(name = "s_cities", sequenceName = "s_cities", allocationSize = 1)
    private Long id;

    @NotNull
    @Column(nullable = false)
    private String cityName;

    @AttributeOverrides({
            @AttributeOverride(name = "cityName",
                    column = @Column(name = "address_city_name"))
    })
    private CityAddress address;

    @Embedded
    private PopulationData populationData;

}
