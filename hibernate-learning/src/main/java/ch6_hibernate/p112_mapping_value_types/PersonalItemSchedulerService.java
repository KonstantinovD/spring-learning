package ch6_hibernate.p112_mapping_value_types;

import ch6_hibernate.p112_mapping_value_types.dictionary.PersonalItemRarity;
import ch6_hibernate.p112_mapping_value_types.dictionary.PersonalItemType;
import ch6_hibernate.p112_mapping_value_types.repository.PersonalItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@Service
@RequiredArgsConstructor
public class PersonalItemSchedulerService {

    private final PersonalItemRepository repository;
    private final JdbcTemplate jdbcTemplate;

    @Transactional
    @Scheduled(fixedDelay = 60000, initialDelay = 1000)
    public void process() {
        repository.deleteAll();
        repository.saveAndFlush(
                new PersonalItem().setType(PersonalItemType.Swords).setRarity(PersonalItemRarity.RARE));

        var personalItem = repository.findAll().stream().findFirst().get();
        List<Map<String, Object>> itemsInDb = jdbcTemplate.queryForList("select * from personal_items");
        var personalItemMap = itemsInDb.get(0);
        assertTrue(personalItemMap.get("TYPE") instanceof Integer);
        assertEquals((Integer) personalItemMap.get("TYPE"), personalItem.getType().ordinal());
        assertTrue(personalItemMap.get("RARITY") instanceof String);
        assertEquals(PersonalItemRarity.valueOf((String) personalItemMap.get("RARITY")), personalItem.getRarity());
    }

}
