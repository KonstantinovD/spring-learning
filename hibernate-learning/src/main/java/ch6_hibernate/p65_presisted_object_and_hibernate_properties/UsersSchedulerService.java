package ch6_hibernate.p65_presisted_object_and_hibernate_properties;

import ch6_hibernate.p65_presisted_object_and_hibernate_properties.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;


@Slf4j
@Service
@RequiredArgsConstructor
public class UsersSchedulerService {

    private final UsersRepository repository;
    private final JdbcTemplate jdbcTemplate;

    @Transactional // иначе не будет работать lazy fetch type
    // https://stackoverflow.com/questions/11746499/how-to-solve-the-failed-to-lazily-initialize-a-collection-of-role-hibernate-ex
    // "failed to lazily initialize a collection of role” Hibernate exception
    @Scheduled(fixedDelay = 60000, initialDelay = 1000)
    public void process() {
        repository.deleteAll();
        testHibernateProperties();
        testSettingNewCollection();
    }

    private void testHibernateProperties() {
        User user = new User();
        user.setFirstName("Daniil");
        user.setLastName("Konstantinov");
        user.setNotes(new ArrayList<>());

        User saved = repository.saveAndFlush(user); // чтобы ченжи были видны в рамках транзакции
        Map<String, Object> userInDb = jdbcTemplate.queryForMap("select * from users");
        // проверяем, что два поля из класса кладутся в одну колонку в базе
        assertEquals(0, saved.getId().compareTo((BigDecimal) userInDb.get("ID")));
        assertEquals(userInDb.get("NAME"), user.getName());
        assertEquals(userInDb.get("NAME"), user.getFirstName() + ' ' + user.getLastName());
    }

    private void testSettingNewCollection() {
        User user = repository.findAll().get(0);
        // добавляем notes и сохраняем
        user.addNote("NOTE1");
        user.addNote("NOTE2");
        repository.saveAndFlush(user);

        List<Map<String, Object>> notesInDb = jdbcTemplate.queryForList("select * from notes");
        assertEquals(2, notesInDb.size());

        // для коллекций проверяется идентичность - добавляются новые entities в базу
        // хотя по факту entities внутри коллекции равны
        user.setNotes(new ArrayList<>());
        user.addNote("NOTE1");
        user.addNote("NOTE2");
        repository.saveAndFlush(user); // казалось, notes останется два...

        List<Map<String, Object>> notesInDb2 = jdbcTemplate.queryForList("select * from notes");
        // ... но нет :)
        assertEquals(4, notesInDb2.size());
        // если же сделать 'orphanRemoval = true' для удаления лишнего
        // то получим следующий эксепшн:
        // A collection with cascade="all-delete-orphan" was no longer referenced
        // by the owning entity instance: User.notes
        User savedEntity = repository.getById(user.getId());
        // сейчас только 2 объекта... но после транзакции будет 4
        assertEquals(2, savedEntity.getNotes().size());
    }

}
