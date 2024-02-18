package ch6_hibernate.p360_EntityGraph;

import ch6_hibernate.common.TransactionalProcessor;
import ch6_hibernate.p360_EntityGraph.entity.Item;
import ch6_hibernate.p360_EntityGraph.entity.Seller;
import ch6_hibernate.p360_EntityGraph.repository.ItemRepository;
import ch6_hibernate.p360_EntityGraph.repository.SellerRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;


@Slf4j
@Service
@AllArgsConstructor
public class EntityGraphScheduler {

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
        });
        System.out.println("---------------------------------------");

        log.info("HIBERNATE TEST completed successfully");
    }

    private Seller generateSeller(String sellerName, String... itemNames) {
        var extId = UUID.randomUUID().toString().substring(0, 10);
        Seller seller = new Seller().setExternalId(extId).setName(sellerName);
        for (String name : itemNames) {
            Item item = new Item().setSeller(seller).setName(name)
                    .setExternalId(UUID.randomUUID()).setItemDate(LocalDate.now())
                    .setCode(UUID.randomUUID().toString().substring(0, 8))
                    .setAmount(BigDecimal.valueOf((long) ((double) 100 * Math.random()), 2));
            seller.getItems().add(item);
        }
        return seller;
    }

}
