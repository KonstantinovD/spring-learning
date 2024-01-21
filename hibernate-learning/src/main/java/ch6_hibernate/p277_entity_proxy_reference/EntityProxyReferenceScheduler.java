package ch6_hibernate.p277_entity_proxy_reference;

import ch6_hibernate.common.TransactionalProcessor;
import ch6_hibernate.p277_entity_proxy_reference.entity.User;
import ch6_hibernate.p277_entity_proxy_reference.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Hibernate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


@Slf4j
@Service
public class EntityProxyReferenceScheduler {

    private static final String USER_NAME_1 = "Mikhail";
    private static final String USER_NAME_2 = "Andrew";
    private static final String USER_NAME_3 = "Alexandra";

    @PersistenceContext
    private EntityManager em;
    private final UserRepository userRepository;
    private final TransactionalProcessor transactionalProcessor;

    private EntityProxyReferenceScheduler(
            UserRepository userRepository,
            TransactionalProcessor transactionalProcessor) {
        this.userRepository = userRepository;
        this.transactionalProcessor = transactionalProcessor;
    }

    @Scheduled(fixedDelay = 60000, initialDelay = 1000)
    public void process() {

        AtomicInteger userIdFirst = new AtomicInteger(0);
        AtomicInteger userIdSecond = new AtomicInteger(0);

        transactionalProcessor.runInNewTransaction(() -> {
            User user1 = new User().setName(USER_NAME_1);
            User user2 = new User().setName(USER_NAME_2);
            userRepository.save(user1);
            userRepository.save(user2);
            userIdFirst.set(user1.getId().intValue());
            userIdSecond.set(user2.getId().intValue());
        });

        transactionalProcessor.runInNewTransaction(() -> {
            User user1 = em.getReference(User.class, BigDecimal.valueOf(userIdFirst.get()));
            assertFalse(isInitialized(user1));
            assertEquals(user1.getName(), USER_NAME_1);
            assertTrue(isInitialized(user1));

            User user2 = em.getReference(User.class, BigDecimal.valueOf(userIdSecond.get()));
            assertFalse(isInitialized(user2));
            Hibernate.initialize(user2);
            assertTrue(isInitialized(user2));
            assertEquals(user2.getName(), USER_NAME_2);
            assertTrue(isInitialized(user2));

            User user3 = new User().setName(USER_NAME_3);
            userRepository.save(user3);
            User userFromDb = em.getReference(User.class, user3.getId());
            assertTrue(isInitialized(userFromDb));
        });

        log.info("ENDED");
    }

    private boolean isInitialized(User user) {
        return em.getEntityManagerFactory()
                .getPersistenceUnitUtil().isLoaded(user);
    }

}
