package spring.sn005_inner_transaction.service;

import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.support.TransactionTemplate;
import spring.sn005_inner_transaction.entity.TestNote;
import spring.sn005_inner_transaction.repository.TestNoteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;


@Slf4j
@Service
@RequiredArgsConstructor
public class TransactionService {

    private final PlatformTransactionManager transactionManager;
    private final TestNoteRepository repository;

    @Transactional
    public void transactionA(List<String> modifiedNames, List<String> modifiedTexts) {
        List<TestNote> notes = repository.findAll();
        int i = 0;
        for (TestNote note : notes) {
            String newText = modifiedTexts.get(i);
            String newName = modifiedNames.get(i);
            i++;
            if (i > 2) {
                throw new RuntimeException("Тестируемое исключение");
            }

            TransactionTemplate transactionTemplate = new TransactionTemplate(transactionManager);
            // new transaction
            transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
            transactionTemplate.execute(status -> {
                // модификация, которая сохранится, потому что она в отдельной транзакции
                transactionB(note, newText);
                return null;
            });
            // модификация имени, которая будет откатываться
            note.setName(newName);
            repository.save(note);

        }
    }

    // над методом может и не быть значка @Transactional
    // - все равно при вызовах из класса он не работает
    @Transactional
    public void transactionB(TestNote note, String newText) {
        note.setText(newText);
        repository.save(note);
    }

    @Transactional
    public void transactionLocked(String text) {
        TestNote note = repository.findByName("ELEMENT_1");

        TransactionTemplate transactionTemplate = new TransactionTemplate(transactionManager);
        // new transaction на залоченных данных
        transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
        transactionTemplate.execute(status -> {
            try {
                transactionB(note, text);
                return null;
            } catch (Exception ex) {
                // эксепшн сюда не ловится, бесполезно
                return null;
            }
        });

        // сюда просто не доходит
        note.setName(UUID.randomUUID().toString());
        repository.save(note);

    }
}
