package ch6_hibernate.p351_batch_size_np1_solution;

import ch6_hibernate.common.TransactionalProcessor;
import ch6_hibernate.p351_batch_size_np1_solution.entity.Item;
import ch6_hibernate.p351_batch_size_np1_solution.entity.Seller;
import ch6_hibernate.p351_batch_size_np1_solution.repository.ItemRepository;
import ch6_hibernate.p351_batch_size_np1_solution.repository.SellerRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;


@Slf4j
@Service
@AllArgsConstructor
public class BatchSizeAnnotationScheduler {

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

        AtomicLong itemId0 = new AtomicLong();
        AtomicLong itemId1 = new AtomicLong();
        AtomicLong itemId2 = new AtomicLong();
        AtomicLong sellerId0 = new AtomicLong();
        transactionalProcessor.runInNewTransaction(() -> {
            var sellers = sellerRepository.findAll();
            // N+1 проблема - происходит LAZY-инициализация N раз
            sellers.forEach(seller ->
                    log.info(
                            "Количество товаров у продавца {}: {}",
                            seller.getName(),
                            seller.getItems().size()));
            // инициализация ID для дальнейшей работы
            itemId0.set(sellers.get(0).getItems().get(0).getId());
            itemId1.set(sellers.get(1).getItems().get(0).getId());
            itemId2.set(sellers.get(2).getItems().get(0).getId());
            sellerId0.set(sellers.get(0).getId());
            // N+1 проблема решена - получаем 2 доп запроса вместо 6
            // Hibernate:
            // select seller_id, id, name from items where seller_id in (?, ?, ?, ?, ?)
            // select seller_id, id, name from items where seller_id=?
        });
        System.out.println("---------------------------------------");



        transactionalProcessor.runInNewTransaction(() -> {
            // N+1 проблема решена - получаем 2 доп запроса вместо 6
            var items = itemRepository.findAll();
            // Hibernate:
            // select id, name, seller_id from items
            // select id, external_id, name from sellers where id in (?, ?, ?, ?, ?)
            // select id, external_id, name from sellers where id=?
            log.info("Общее количество товаров: {}", items.size());

        });
        System.out.println("---------------------------------------");

        transactionalProcessor.runInNewTransaction(() -> {
            // N+1 проблема решена - получаем 1 доп запроc вместо 3
            var items = itemRepository.findAllById(
                    List.of(itemId0.get(), itemId1.get(), itemId2.get()));
            // Hibernate:
            // select id, name, seller_id from items
            // select id, external_id, name from sellers where id in (?, ?, ?)
            log.info("Общее количество найденных по id товаров: {}", items.size());
        });
        System.out.println("---------------------------------------");

        log.info("HIBERNATE TEST completed successfully");
    }

    private Seller generateSeller(String sellerName, String... itemNames) {
        var extId = UUID.randomUUID().toString().substring(0, 10);
        Seller seller = new Seller().setExternalId(extId).setName(sellerName);
        for (String name : itemNames) {
            Item item = new Item().setSeller(seller).setName(name);
            seller.getItems().add(item);
        }
        return seller;
    }

}
