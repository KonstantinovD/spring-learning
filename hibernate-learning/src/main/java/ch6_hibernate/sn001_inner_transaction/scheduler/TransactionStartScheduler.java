package ch6_hibernate.sn001_inner_transaction.scheduler;

import ch6_hibernate.sn001_inner_transaction.entity.TestNote;
import ch6_hibernate.sn001_inner_transaction.repository.TestNoteRepository;
import ch6_hibernate.sn001_inner_transaction.service.TransactionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.PessimisticLockingFailureException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;


@Slf4j
@Service
@RequiredArgsConstructor
public class TransactionStartScheduler {

    @Value("${my-config.test}")
    private String test;

    private final TransactionService service;
    private final TestNoteRepository repository;


    @Scheduled(fixedDelay = 20000, initialDelay = 1000)
    public void process() {
        runNestedTransaction();
        runLockedNestedTransaction();
    }

    private void runNestedTransaction() {
        List<TestNote> initial = repository.findAll();
        assertEquals(3, initial.size());

        List<String> modifiedNames = IntStream.range(0, initial.size())
                .mapToObj(i -> RandomStringUtils
                        .random(10, true, false)).collect(Collectors.toList());
        List<String> modifiedTexts = IntStream.range(0, initial.size())
                .mapToObj(i -> RandomStringUtils
                        .random(10, true, false)).collect(Collectors.toList());

        try {
            service.transactionA(modifiedNames, modifiedTexts);
        } catch (Exception ex) {
            log.error("Ошибка во время выполнения транзакции: {}", ex.getMessage());
        }

        assertEquals(3, repository.count());
        AtomicInteger i = new AtomicInteger(0);
        initial.forEach(initialNote -> {
            TestNote modifiedNote = repository.findById(initialNote.getId()).get();
            assertEquals(modifiedNote.getName(), initialNote.getName());
            if (i.get() == 2) {
                assertEquals(modifiedNote.getText(), initialNote.getText());
            } else {
                assertEquals(modifiedNote.getText(), modifiedTexts.get(i.get()));
                assertNotEquals(modifiedNote.getText(), initialNote.getText());
            }
            i.incrementAndGet();
        });
    }

    private void runLockedNestedTransaction() {
        String newText = RandomStringUtils
                .random(10, true, false);

        PessimisticLockingFailureException exception =
                assertThrows(PessimisticLockingFailureException.class,
                        () -> service.transactionLocked(newText));
        assertTrue(exception.getMessage().contains(
                "could not execute batch; SQL [update test_table set name=?, text=? where id=?]"));
        exception.printStackTrace();
    }
}
