package ch6_hibernate.p361_SqlResultSetMapping;

import ch6_hibernate.common.TransactionalProcessor;
import ch6_hibernate.p361_SqlResultSetMapping.dictionary.AttributeCategory;
import ch6_hibernate.p361_SqlResultSetMapping.entity.Item;
import ch6_hibernate.p361_SqlResultSetMapping.entity.ItemAttribute;
import ch6_hibernate.p361_SqlResultSetMapping.repository.ItemAttributeRepository;
import ch6_hibernate.p361_SqlResultSetMapping.repository.ItemRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;


@Slf4j
@Service
@AllArgsConstructor
public class SqlResultSetMappingScheduler {

    @PersistenceContext
    private EntityManager em;

    private final ItemRepository itemRepository;
    private final ItemAttributeRepository attributeRepository;
    private final TransactionalProcessor transactionalProcessor;

    @Scheduled(fixedDelay = 60000, initialDelay = 1000)
    public void process() {
        itemRepository.deleteAll();
        attributeRepository.deleteAll();

        LocalDate current = LocalDate.now();
        List<UUID> itemIds = new ArrayList<>();
        transactionalProcessor.runInNewTransaction(() -> {
            var items = itemRepository.saveAll(List.of(
                    generateItem("Ламинат Дуб Сонома", current, Map.of(
                            "Производитель", "FloorPan",
                            "Артикул", "171400923"

                    )),
                    generateItem("Ламинат Дуб Полярный", current, Map.of(
                            "Производитель", "Egger",
                            "Артикул", "171403555"

                    )),
                    generateItem("Плитка Onyx Blanco Laparet", current, Map.of(
                            "Артикул", "166184027"
                    )),
                    generateItem("Плитка Onyx Laparet", current, Map.of(
                            "Артикул", "166184031",
                            "Назначение", "Стены"
                    )),
                    generateItem("Плитка Onyx Cloudy Laparet", current, Map.of(
                            "Артикул", "166184030"
                    )),
                    generateItem("Плитка Eternum ФУМЭ Italon", current, Map.of(
                            "Артикул", "166120117"
                    ))
            ));
            items.forEach(item -> itemIds.add(item.getExternalId()));
        });
        System.out.println("---------------------------------------");

        transactionalProcessor.runInNewTransaction(() -> {
            // N+1 проблема - даже entity graph не помогает
            // из-за @Id Item на ItemAttribute
            var attributes = attributeRepository.findAll();
            // Hibernate:
            // select code, item_id, item_date, value from item_attributes
            // select external_id, item_date, amount, code, name from items where external_id=? and item_date=?
            // select external_id, item_date, amount, code, name from items where external_id=? and item_date=?
            // select external_id, item_date, amount, code, name from items where external_id=? and item_date=?
            // select external_id, item_date, amount, code, name from items where external_id=? and item_date=?
            // select external_id, item_date, amount, code, name from items where external_id=? and item_date=?
            // select external_id, item_date, amount, code, name from items where external_id=? and item_date=?
            log.info("Общее количество аттрибутов: {}", attributes.size());
            // Причем это работает даже если fk не composite
        });
        System.out.println("---------------------------------------");

        transactionalProcessor.runInNewTransaction(() -> {
            // N+1 проблема решена т к в самой DTO нету ссылки на Item - это не Entity
            //
            // Однако есть некоторые ограничения в конвертации - например, нельзя поставить Enum в параметры
            // Поэтому невозможно в category установить поле типа AttributeCategory.INT
            var attributes = attributeRepository.findByItemIdInAndItemDate(
                    itemIds, current, AttributeCategory.INT.name());
            // Hibernate:
            // select code, item_id, item_date, value, category from item_attributes
            // where item_id in (?, ?, ?, ?, ?, ?) and item_date = ? and category = ?
            log.info("Общее количество аттрибутов категории INT: {}", attributes.size());
            // Общее количество аттрибутов категории INT: 6
        });
        System.out.println("---------------------------------------");

        log.info("HIBERNATE TEST completed successfully");
    }

    private Item generateItem(String itemName, LocalDate date, Map<String, String> attrs) {
        Item item = new Item().setName(itemName)
                .setExternalId(UUID.randomUUID()).setItemDate(date)
                .setCode(UUID.randomUUID().toString().substring(0, 8))
                .setAmount(BigDecimal.valueOf((long) ((double) 100 * Math.random()), 2));

        attrs.forEach((code, value) -> {
            var attr = new ItemAttribute().setItem(item).setCode(code).setValue(value);
            try {
                var ignored = Integer.parseInt(value);
                attr.setCategory(AttributeCategory.INT);
            } catch (NumberFormatException ignored) {
                attr.setCategory(AttributeCategory.TEXT);
            }
            item.getAttrs().add(attr);
        });
        return item;
    }

}
