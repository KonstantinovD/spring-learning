package ch6_hibernate.p347_n_plus_1_problem;

import ch6_hibernate.common.TransactionalProcessor;
import ch6_hibernate.p347_n_plus_1_problem.entity.Item;
import ch6_hibernate.p347_n_plus_1_problem.entity.Seller;
import ch6_hibernate.p347_n_plus_1_problem.repository.ItemRepository;
import ch6_hibernate.p347_n_plus_1_problem.repository.SellerRepository;
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
public class OneToOneFetchingScheduler {

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
        });
        System.out.println("---------------------------------------");

        transactionalProcessor.runInNewTransaction(() -> {
            // N+1 проблема - выполняется сразу,
            // т. к. связь item->seller имеет тип EAGER
            var items = itemRepository.findAll();
            log.info("Общее количество товаров: {}", items.size());
        });
        System.out.println("---------------------------------------");

        transactionalProcessor.runInNewTransaction(() -> {
            // N+1 проблема - выполняется сразу,
            // т. к. связь item->seller имеет тип EAGER
            var items = itemRepository.findAllById(List.of(itemId0.get(), itemId1.get(), itemId2.get()));
            log.info("Общее количество найденных по id товаров: {}", items.size());
        });
        System.out.println("---------------------------------------");

        transactionalProcessor.runInNewTransaction(() -> {
            // Hibernate: выборка двух сущностей через join
            var item0 = em.find(Item.class, itemId1.get());
            log.info("Продавец: {}", item0.getSeller().getName());

            // Hibernate: выборка двух сущностей через join
            var item1 = itemRepository.findById(itemId2.get()).get();
            log.info("Продавец: {}", item1.getSeller().getName());

            // Hibernate: выборка двух сущностей через два запроса
            var seller = sellerRepository.findById(sellerId0.get()).get();
            log.info("Товары: {}", seller.getItems());
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
