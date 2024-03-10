package ch6_hibernate.p378_interceptors;

import ch6_hibernate.common.TransactionalProcessor;
import ch6_hibernate.p378_interceptors.entity.Item;
import ch6_hibernate.p378_interceptors.entity.Seller;
import ch6_hibernate.p378_interceptors.repository.ItemRepository;
import ch6_hibernate.p378_interceptors.repository.SellerRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


@Slf4j
@Service
@AllArgsConstructor
public class InterceptorTestScheduler {

    @PersistenceContext
    private EntityManager em;

    private final SellerRepository sellerRepository;
    private final ItemRepository itemRepository;
    private final TransactionalProcessor transactionalProcessor;

    @Scheduled(fixedDelay = 60000, initialDelay = 1000)
    public void process() {
        sellerRepository.deleteAll();
        itemRepository.deleteAll();

        transactionalProcessor.runInNewTransaction(() ->
                sellerRepository.saveAll(List.of(
                        generateSeller("Хозмаг", "Ламинат", "Плитка"),
                        generateSeller("Продуктовый", "Яблочный сок"),
                        generateSeller("Одежда", "Свитер"),
                        generateSeller("Обувной", "Кроссовки"),
                        generateSeller("Мебельный", "Диван"),
                        generateSeller("Кафе", "Кофе")
                )));
        System.out.println("---------------------------------------");

        transactionalProcessor.runInNewTransaction(() -> {
            // N+1 проблема решена - запросов к seller нет вообще
            // И даже composite PK не мешает
            var items2 = itemRepository.findAll();
            // Hibernate:
            // select external_id, item_date, amount, code, name, seller_id from items
            log.info("Общее количество товаров: {}", items2.size());
            items2.get(0).setAmount(BigDecimal.valueOf(1.05));
            items2.get(0).setTwiceId(UUID.randomUUID());

            items2.get(0).setIntAmount1(101);
            items2.get(0).setIntAmount3(items2.get(0).getIntAmount3());
            items2.get(0).setIntAmount4(500);

            items2.get(0).setLongAmount1(101);
            items2.get(0).setLongAmount3(items2.get(0).getLongAmount3());
            items2.get(0).setLongAmount4(500L);
        });
        System.out.println("---------------------------------------");

        log.info("HIBERNATE TEST completed successfully");
    }

    private Seller generateSeller(String sellerName, String... itemNames) {
        var extId = UUID.randomUUID().toString().substring(0, 10);
        Seller seller = new Seller().setExternalId(extId).setName(sellerName);
        for (String name : itemNames) {
            Item item = new Item().setSeller(seller).setName(name)
                    .setDateValue(LocalDate.now().minusDays(11))
                    .setTimeValue(LocalDateTime.now().minusHours(11))
                    .setTwiceId(UUID.randomUUID())
                    .setExternalId(UUID.randomUUID()).setItemDate(LocalDate.now())
                    .setCode(UUID.randomUUID().toString().substring(0, 8))
                    .setAmount(BigDecimal.valueOf((long) ((double) 100 * Math.random()), 2));
            initAmounts(item);
            seller.getItems().add(item);
        }
        return seller;
    }

    private void initAmounts(Item item) {
        int baseValue = (int) (100 * Math.random());
        int baseValue2 = (int) (100 * Math.random()) + 200;
        item.setIntAmount1(baseValue);
        item.setIntAmount2(baseValue);
        item.setIntAmount3(baseValue2);
        item.setIntAmount4(baseValue2);

        item.setLongAmount1(baseValue);
        item.setLongAmount2(baseValue);
        item.setLongAmount3((long) baseValue2);
        item.setLongAmount4((long) baseValue2);
    }

}
