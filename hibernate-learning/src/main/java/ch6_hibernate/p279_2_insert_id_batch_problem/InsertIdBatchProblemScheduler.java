package ch6_hibernate.p279_2_insert_id_batch_problem;

import ch6_hibernate.common.TransactionalProcessor;
import ch6_hibernate.p279_2_insert_id_batch_problem.entity.Address;
import ch6_hibernate.p279_2_insert_id_batch_problem.entity.User;
import ch6_hibernate.p279_2_insert_id_batch_problem.repository.AddressRepository;
import ch6_hibernate.p279_2_insert_id_batch_problem.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * СМОТРИ ЗА ЛОГАМИ HIBERNATE
 */

@Slf4j
@Service
@AllArgsConstructor
public class InsertIdBatchProblemScheduler {

    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final TransactionalProcessor transactionalProcessor;

    @Scheduled(fixedDelay = 60000, initialDelay = 1000)
    public void process() {
        addressRepository.deleteAll();
        userRepository.deleteAll();
        User user1 = new User().setName("Viktor");
        User user2 = new User().setName("Grigory");
        Address address1 = new Address().setCountryName("Spain").setCityName("Madrid");
        Address address2 = new Address().setCountryName("France").setCityName("Paris");
        transactionalProcessor.runInNewTransaction(() -> {
            userRepository.save(user1);
            userRepository.save(user2);
            // если делать saveAll (и даже менять allocationSize) -
            // все равно 'call next value for s_users' будет вызываться дважды
            var addresses = List.of(address1, address2);
            addressRepository.saveAll(addresses);
            int ignored = 2;
        });

        log.info("HIBERNATE TEST completed successfully");
    }

}
