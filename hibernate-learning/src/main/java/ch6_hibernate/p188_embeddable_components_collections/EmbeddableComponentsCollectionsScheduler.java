package ch6_hibernate.p188_embeddable_components_collections;

import ch6_hibernate.p188_embeddable_components_collections.entity.Image;
import ch6_hibernate.p188_embeddable_components_collections.entity.Item;
import ch6_hibernate.p188_embeddable_components_collections.repository.ItemRepository;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@Service
@AllArgsConstructor
public class EmbeddableComponentsCollectionsScheduler {

    private final ItemRepository repository;
    private final JdbcTemplate jdbcTemplate;

    @Scheduled(fixedDelay = 60000, initialDelay = 1000)
    public void process() {
        repository.deleteAll();

        Item item = new Item().setName("refrigerator");

        Image image1 = new Image().setFilename("refrig1.png").setTitle("front side").setHeight(535).setWidth(317);
        Image image2 = new Image().setFilename("refrig2.png").setTitle("side side").setHeight(511).setWidth(300);

        item.getImages().add(image1);
        item.getImages().add(image2);
        item.getProperties().addAll(List.of("No Frost", "Black", "Samsung"));

        item = repository.save(item);

        Map<String, Object> itemInDb = jdbcTemplate.queryForList("select * from items").get(0);
        List<Map<String, Object>> imagesInDb = jdbcTemplate.queryForList("select * from images");
        List<Map<String, Object>> propertiesInDb = jdbcTemplate.queryForList("select * from properties");

        checkItem(itemInDb, item);
        checkImages(imagesInDb, item);
        checkProperties(propertiesInDb, item);
    }

    void checkItem(Map<String, Object> itemInDb, Item item) {
        assertEquals(itemInDb.get("ID"), item.getId());
        assertEquals(itemInDb.get("NAME"), item.getName());
    }

    void checkImages(List<Map<String, Object>> imagesInDb, Item item) {
        assertEquals(item.getImages().size(), imagesInDb.size());
        imagesInDb.forEach(imageInDb -> {
            assertEquals(item.getId(), imageInDb.get("ITEM_ID"));

            var imageEmbedded = item.getImages()
                    .stream().filter(image -> image.getFilename().equals(imageInDb.get("FILENAME")))
                    .findAny().get();
            assertEquals(imageEmbedded.getTitle(), imageInDb.get("TITLE"));
            assertEquals(imageEmbedded.getWidth(), imageInDb.get("WIDTH"));
            assertEquals(imageEmbedded.getHeight(), imageInDb.get("HEIGHT"));
        });
    }

    void checkProperties(List<Map<String, Object>> propertiesInDb, Item item) {
        assertEquals(item.getProperties().size(), propertiesInDb.size());
        propertiesInDb.forEach(propertyInDb -> {
            assertEquals(item.getId(), propertyInDb.get("ITEM_ID"));
            assertTrue(item.getProperties().contains((String) propertyInDb.get("PROPERTY")));
        });
    }

}
