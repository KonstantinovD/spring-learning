package ch6_hibernate.p334_one_to_one_lazy_and_eager_fetching;

import ch6_hibernate.common.TransactionalProcessor;
import ch6_hibernate.p334_one_to_one_lazy_and_eager_fetching.entity.Item;
import ch6_hibernate.p334_one_to_one_lazy_and_eager_fetching.repository.ItemRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Hibernate;
import org.hibernate.proxy.HibernateProxyHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@Slf4j
@Service
@AllArgsConstructor
public class OneToOneFetchingScheduler {

    @PersistenceContext
    private EntityManager em;

    private final ItemRepository itemRepository;
    private final TransactionalProcessor transactionalProcessor;

    @Scheduled(fixedDelay = 60000, initialDelay = 1000)
    public void process() {
        itemRepository.deleteAll();

        AtomicLong itemId = new AtomicLong();

        transactionalProcessor.runInNewTransaction(() -> {
            Item item = new Item();
            item.setName("Cleaner");
            itemRepository.save(item);
            itemId.set(item.getId());
        });

        List<Item> holder = new ArrayList<>();
        // важно - на debug-е инициализация произойдёт
        transactionalProcessor.runInNewTransaction(() -> {
            // Никакого SELECT
            Item item = em.getReference(Item.class, itemId.get());
            // Вызов метода чтения идентификатора (без доступа
            // к членам класса!) не вызовет инициализацию
            assertEquals(item.getId(), itemId.get());

            // Прокси-класс сгенерирован во время выполнения и получает
            // примерно такое имя: Item$HibernateProxy$hkR6xO8q
            assertNotEquals(item.getClass(), Item.class);
            assertTrue(item.getClass().getSimpleName().startsWith("Item$HibernateProxy"));
            // Для получения реального типа прокси используйте класс HibernateProxyHelper.
            assertEquals(
                    HibernateProxyHelper.getClassWithoutInitializingProxy(item),
                    Item.class
            );

            PersistenceUtil persistenceUtil = Persistence.getPersistenceUtil();
            assertFalse(persistenceUtil.isLoaded(item));
            // Позволяет проверить, загружено ли значение поля
            // Обычно используется с LAZY-fetch
            assertFalse(persistenceUtil.isLoaded(item, "name"));
            assertFalse(Hibernate.isInitialized(item));
            // assertFalse(Hibernate.isInitialized(item.getSeller())); Вызовет инициализацию item!

            holder.add(item);
        });

        Item outOfTransactionItem = holder.get(0);
        PersistenceUtil persistenceUtil = Persistence.getPersistenceUtil();
        assertFalse(persistenceUtil.isLoaded(outOfTransactionItem, "name"));
        assertTrue(outOfTransactionItem instanceof Item);

        log.info("HIBERNATE TEST completed successfully");
    }

}
