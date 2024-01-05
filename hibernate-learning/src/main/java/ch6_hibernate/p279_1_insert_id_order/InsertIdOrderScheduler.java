package ch6_hibernate.p279_1_insert_id_order;

import ch6_hibernate.common.TransactionalProcessor;
import ch6_hibernate.p279_1_insert_id_order.entity.Address;
import ch6_hibernate.p279_1_insert_id_order.entity.User;
import ch6_hibernate.p279_1_insert_id_order.repository.AddressRepository;
import ch6_hibernate.p279_1_insert_id_order.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;


/**
 * СМОТРИ ЗА ЛОГАМИ HIBERNATE
 */

@Slf4j
@Service
@AllArgsConstructor
public class InsertIdOrderScheduler {

    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final TransactionalProcessor transactionalProcessor;

    @Scheduled(fixedDelay = 60000, initialDelay = 1000)
    public void process() {
        addressRepository.deleteAll();
        userRepository.deleteAll();
        User user = new User().setName("Viktor");
        User user2 = new User().setName("Grigory");
        User user3 = new User().setName("Mike");
        Address address = new Address().setCountryName("Spain").setCityName("Madrid");
        Address address2 = new Address().setCountryName("France").setCityName("Paris");
        transactionalProcessor.runInNewTransaction(() -> {
            userRepository.save(user);
            userRepository.save(user2);
            // ЛОГИ HIBERNATE - insert user-ов еще не произошел, Id создаются ДО insert-а
            // Hibernate: call next value for s_users
            // Hibernate: call next value for s_users
            addressRepository.save(address);
            // ЛОГИ HIBERNATE - произошел insert user-ов и address, у которого Id создается ВО ВРЕМЯ insert-а
            // Hibernate: insert into users (name, id) values (?, ?)
            // Hibernate: insert into users (name, id) values (?, ?)
            // Hibernate: insert into addresses (id, city_name, country_name, user_id) values (null, ?, ?, ?)
            userRepository.save(user3);
            // ЛОГИ HIBERNATE - insert user-а еще не произошел
            // Hibernate: call next value for s_users
            address2.setUser(user);
            addressRepository.saveAndFlush(address2);
            // ЛОГИ HIBERNATE - произошел insert user и address
            // Hibernate: insert into users (name, id) values (?, ?)
            // Hibernate: insert into addresses (id, city_name, country_name, user_id) values (null, ?, ?, ?)
            int ignored = 11;
        });

        System.out.println("--- HB LOGS SEPARATOR ---");
        Address address3 = new Address().setCountryName("Thailand").setCityName("Bangkok");
        transactionalProcessor.runInNewTransaction(() -> {
            var savedUser = userRepository.findById(user2.getId()).get();
            userRepository.delete(savedUser);
            savedUser = userRepository.findById(user3.getId()).get();
            savedUser.setName("Oleg"); // Mike -> Oleg
            userRepository.save(savedUser);
            addressRepository.save(address3);
            int ignored = 11;
            // ЛОГИ HIBERNATE - произошел только insert address
            // Hibernate: insert into addresses (id, city_name, country_name, user_id) values (null, ?, ?, ?)
        });
        // ЛОГИ HIBERNATE - и только по окончанию транзакции - update и delete
        // Hibernate: update users set name=? where id=?
        // Hibernate: delete from users where id=?

        log.info("HIBERNATE TEST completed successfully");
    }

}
