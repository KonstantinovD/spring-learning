package ch6_hibernate.p129_jpa_converters;

import ch6_hibernate.p129_jpa_converters.dto.MonetaryAmount;
import ch6_hibernate.p129_jpa_converters.entity.Price;
import ch6_hibernate.p129_jpa_converters.repository.PriceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;


@Service
@RequiredArgsConstructor
public class JpaConvertersScheduler {

    private final PriceRepository repository;
    private final JdbcTemplate jdbcTemplate;

    @Scheduled(fixedDelay = 60000, initialDelay = 1000)
    public void process() {
        repository.deleteAll();
        var price = new Price().setItemId("Хлебушек")
                .setPrice(
                        new MonetaryAmount(
                                new BigDecimal("2.25"),
                                Currency.getInstance("BYR")));

        var priceId = repository.save(price).getId();
        List<Map<String, Object>> itemsInDb =
                jdbcTemplate.queryForList(String.format("select * from prices where id=%d", priceId));
        var savedPrice = repository.findById(priceId).get();
        assertDBOutput(itemsInDb.get(0), savedPrice);
    }

    static void assertDBOutput(Map<String, Object> priceMap, Price price) {
        assertEquals(price.getPrice().getValue(), new BigDecimal("2.25"));
        assertEquals(price.getPrice().getCurrency().getCurrencyCode(), "BYR");
        assertEquals(price.getItemId(), "Хлебушек");

        assertEquals(priceMap.get("PRICE"), "2.25 BYR");
        assertEquals(priceMap.get("ITEM_ID"), "Хлебушек");
    }
}
