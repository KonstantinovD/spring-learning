package ch6_hibernate.p123_embedded_embeddable_classes;

import ch6_hibernate.p123_embedded_embeddable_classes.entity.City;
import ch6_hibernate.p123_embedded_embeddable_classes.entity.CityAddress;
import ch6_hibernate.p123_embedded_embeddable_classes.entity.PopulationData;
import ch6_hibernate.p123_embedded_embeddable_classes.repository.CityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;


@Service
@RequiredArgsConstructor
public class EmbeddableClassesScheduler {

    private static final String CITY_NAME_1 = "Gomel";
    private static final String CITY_CAPITALIZED_NAME_1 = "GOMEL";
    private static final String CITY_NAME_2 = "Minsk";
    private static final String COUNTRY_NAME = "Belarus";
    private static final long HUMANS_NUMBER = 501_802L;
    private static final String POPULATION_INFO = "Второй по численности населения город";

    private final CityRepository repository;
    private final JdbcTemplate jdbcTemplate;

    @Scheduled(fixedDelay = 60000, initialDelay = 1000)
    public void process() {
        repository.deleteAll();

        var cityEntity = new City().setCityName(CITY_NAME_1).setPopulationData(
                new PopulationData()
                        .setHumansNumber(HUMANS_NUMBER)
                        .setManPercentage(new BigDecimal("45.543"))
                        .setPopulationInfo(POPULATION_INFO))
                .setAddress(new CityAddress().setCountryName(COUNTRY_NAME).setCityName(CITY_CAPITALIZED_NAME_1));
        var cityId1 = repository.saveAndFlush(cityEntity).getId();
        cityEntity = prepareWithNullEmbeddableClassesProperties();
        var cityId2 = repository.saveAndFlush(cityEntity).getId();

        List<Map<String, Object>> itemsInDb =
                jdbcTemplate.queryForList(String.format("select * from cities where id=%d", cityId1));
        assertDBOutput(itemsInDb.get(0));

        var city2 = repository.findById(cityId2).get();
        assertEquals(CITY_NAME_2, city2.getCityName());
        assertNull(city2.getAddress());
        assertNull(city2.getPopulationData());
    }

    static City prepareWithNullEmbeddableClassesProperties() {
        return new City().setCityName(CITY_NAME_2).setPopulationData(
                        new PopulationData()
                                .setHumansNumber(null)
                                .setManPercentage(null)
                                .setPopulationInfo(null))
                .setAddress(new CityAddress().setCountryName(null).setCityName(null));
    }

    static void assertDBOutput(Map<String, Object> townMap) {
        assertEquals(townMap.get("CITY_NAME"), CITY_NAME_1);
        assertEquals(townMap.get("HUMANS_NUMBER"), HUMANS_NUMBER);
        assertEquals(townMap.get("MAN_PERCENTAGE"), new BigDecimal("45.543000"));
        assertEquals(townMap.get("POPULATION_INFO"), POPULATION_INFO);
        assertEquals(townMap.get("ADDRESS_CITY_NAME"), CITY_CAPITALIZED_NAME_1);
        assertEquals(townMap.get("COUNTRY_NAME"), COUNTRY_NAME);
    }
}
