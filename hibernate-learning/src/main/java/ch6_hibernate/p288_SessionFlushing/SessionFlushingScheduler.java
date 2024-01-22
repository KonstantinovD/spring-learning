package ch6_hibernate.p288_SessionFlushing;

import ch6_hibernate.common.TransactionalProcessor;
import ch6_hibernate.p288_SessionFlushing.entity.User;
import ch6_hibernate.p288_SessionFlushing.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;


/**
 * СМОТРИ ЗА ЛОГАМИ HIBERNATE
 */

@Slf4j
@Service
@AllArgsConstructor
public class SessionFlushingScheduler {

    private final UserRepository userRepository;
    private final TransactionalProcessor transactionalProcessor;
    private final JdbcTemplate jdbcTemplate;

    @Scheduled(fixedDelay = 60000, initialDelay = 1000)
    public void process() {
        userRepository.deleteAll();
        User user = new User().setName("Viktor");
        User user2 = new User().setName("Grigory");
        User user3 = new User().setName("Pavel");
        User user4 = new User().setName("Kondrat");
        transactionalProcessor.runInNewTransaction(() -> {
            userRepository.save(user);
            userRepository.save(user2);
            // ЛОГИ HIBERNATE - insert user-ов еще не произошел
            //
            // Hibernate: call next value for s_users
            // Hibernate: call next value for s_users

            var notExistingId = user.getId().add(user2.getId());
            var userOptional = userRepository.findById(notExistingId);
            // ЛОГИ HIBERNATE - insert user-ов все еще не произошел - т к метод типа find/findById
            //
            // select user0_.id as id1_0_0_, user0_.age as age2_0_0_, user0_.name as name3_0_0_ from users user0_ where user0_.id=?

            var elderUsers = userRepository.findByAgeGreaterThan(17);
            // ЛОГИ HIBERNATE - insert произошел - метод типа query
            // Если включен FlushMode.COMMIT то insert-ов не происходит
            //
            // Hibernate: insert into users (age, name, id) values (?, ?, ?)
            // Hibernate: insert into users (age, name, id) values (?, ?, ?)
            // Hibernate: select user0_.id as id1_0_, user0_.age as age2_0_, user0_.name as name3_0_ from users user0_ where user0_.age>?

            userRepository.save(user3);
            // ЛОГИ HIBERNATE - insert user-а еще не произошел
            // Hibernate: call next value for s_users
            var usersNumber = userRepository.countUsers();
            // ЛОГИ HIBERNATE - произошел insert user-а из-за выполнения @Query
            // Если включен FlushMode.COMMIT то insert не происходит
            //
            // Hibernate: insert into users (age, name, id) values (?, ?, ?)
            // Hibernate: select count(*) as col_0_0_ from users user0_

            userRepository.save(user4);
            jdbcTemplate.queryForList("select * from users where name = Grigory");
            // jdbcTemplate не действует в контексте hibernate - insert user не происходит

            int ignored = 11;
        });
        int ignored = 11;
    }
}
