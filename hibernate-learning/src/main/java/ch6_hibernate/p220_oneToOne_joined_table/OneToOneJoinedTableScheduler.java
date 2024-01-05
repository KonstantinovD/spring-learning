package ch6_hibernate.p220_oneToOne_joined_table;

import ch6_hibernate.p220_oneToOne_joined_table.entity.Address;
import ch6_hibernate.p220_oneToOne_joined_table.entity.User;
import ch6_hibernate.p220_oneToOne_joined_table.repository.AddressRepository;
import ch6_hibernate.p220_oneToOne_joined_table.repository.UserRepository;
import ch6_hibernate.p220_oneToOne_joined_table.utils.TransactionalProcessor;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;


@Slf4j
@Service
@AllArgsConstructor
public class OneToOneJoinedTableScheduler {

    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final JdbcTemplate jdbcTemplate;
    private final TransactionalProcessor transactionalProcessor;

    @Scheduled(fixedDelay = 60000, initialDelay = 1000)
    public void process() {
        userRepository.deleteAll();
        addressRepository.deleteAll();
        User user = new User().setName("Kondrat");
        Address address = new Address().setCountryName("Belarus").setCityName("Gomel");
        Address address2 = new Address().setCountryName("Belarus").setCityName("Minsk");
        transactionalProcessor.runInNewTransaction(() -> {
            userRepository.save(user);
            addressRepository.save(address);
            address2.setUser(user);
            // user.setAddress(address2); // если такое сделать - то будет 2 записи в "user_address"
            // подробнее на https://stackoverflow.com/questions/8641322/hibernate-one-to-many-with-join-table-duplicate-inserts
            addressRepository.saveAndFlush(address2);
        });

        transactionalProcessor.runInNewTransaction(() -> {
            var savedUser = userRepository.findById(user.getId()).get();
            var savedAddress = addressRepository.findById(address2.getId()).get();

            List<Map<String, Object>> usersInDb = jdbcTemplate.queryForList("select * from users");
            List<Map<String, Object>> addressesInDb = jdbcTemplate.queryForList("select * from addresses");
            List<Map<String, Object>> joinedTableInDb = jdbcTemplate.queryForList("select * from user_address");

            checkUsers(usersInDb, savedUser);
            checkAddresses(addressesInDb, savedAddress);
            checkJoinedTable(joinedTableInDb, savedUser, savedAddress);
        });

        log.info("HIBERNATE VALIDATOR TEST completed successfully");
    }

    void checkUsers(List<Map<String, Object>> usersInDb, User user) {
        assertEquals(1, usersInDb.size());
        var userInDb = usersInDb.get(0);
        assertEquals(userInDb.get("ID"), user.getId());
        assertEquals(userInDb.get("NAME"), user.getName());
    }

    void checkAddresses(List<Map<String, Object>> addressesInDb, Address address) {
        assertEquals(2, addressesInDb.size());
        var addressInDb = addressesInDb.get(1);
        assertEquals(addressInDb.get("ID"), address.getId());
        assertEquals(addressInDb.get("COUNTRY_NAME"), address.getCountryName());
        assertEquals(addressInDb.get("CITY_NAME"), address.getCityName());
    }

    void checkJoinedTable(List<Map<String, Object>> joinedTableInDb, User user, Address address) {
        assertEquals(1, joinedTableInDb.size());
        var link = joinedTableInDb.get(0);
        assertEquals(link.get("USER_ID"), user.getId());
        assertEquals(link.get("ADDRESS_ID"), address.getId());
    }

}
