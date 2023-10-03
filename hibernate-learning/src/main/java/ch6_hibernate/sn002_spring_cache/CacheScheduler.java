package ch6_hibernate.sn002_spring_cache;

import ch6_hibernate.sn002_spring_cache.entity.TestNote;
import ch6_hibernate.sn002_spring_cache.repository.TestNoteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@Slf4j
@Service
@RequiredArgsConstructor
public class CacheScheduler {

    private final TestNoteService service;
    private final TestNoteRepository repository;

    // once in 5 min
    @Scheduled(fixedDelay = 300000, initialDelay = 1000)
    public void process() throws InterruptedException {
        List<TestNote> notes1 = repository.findAll();
        assertEquals(3, notes1.size()); // initial

        TestNote newEntity =
                TestNote.builder()
                        .name("NEW_MANE")
                        .text("NEW_TEXT")
                        .build();
        repository.save(newEntity);

        List<TestNote> notes2 = repository.findAll();
        assertEquals(3, notes2.size()); // still 3 notes, 20s left
        Thread.sleep(10000);
        List<TestNote> notes3 = repository.findAll();
        assertEquals(3, notes3.size()); // still 3 notes, 10s left
        Thread.sleep(12000);

        // 4 notes, cache write ttl expired (though we had access, but it wasn't write access)
        List<TestNote> notes4 = repository.findAll();
        assertEquals(4, notes4.size());
        assertEquals(1, notes4.stream().filter(
                testNote -> "NEW_MANE".equals(testNote.getName())
                        && "NEW_TEXT".equals(testNote.getText())).count());

        repository.delete(newEntity);
        List<TestNote> notes5 = repository.findAll();
        assertEquals(4, notes5.size()); // кэш еще живёт
        repository.evictTestCache(); // сбрасываем кэш
        List<TestNote> notes6 = repository.findAll();
        assertEquals(3, notes6.size()); // сразу получаем обновление

        log.info("CACHE TEST completed successfully");
    }
}
