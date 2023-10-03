package ch6_hibernate.sn002_spring_cache;

import ch6_hibernate.sn002_spring_cache.entity.TestNote;
import ch6_hibernate.sn002_spring_cache.repository.TestNoteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Slf4j
@Service
@RequiredArgsConstructor
public class TestNoteService {

    private final TestNoteRepository repository;

    @Transactional
    public List<TestNote> runCache() {
        var v = repository.findAll();
        log.info("{}", v);
        return v;
    }

    @Transactional
    public List<TestNote> testCache() {
        var v = repository.findAll();
        log.info("{}", v);
        return v;
    }
}
