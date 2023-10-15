package ch6_hibernate.p75_bean_hibernate_validator;

import ch6_hibernate.p75_bean_hibernate_validator.repository.ItemsRepository;
import ch6_hibernate.p75_bean_hibernate_validator.service.ItemsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolationException;
import java.time.LocalDate;

import static ch6_hibernate.p75_bean_hibernate_validator.constants.HibernateValidationExampleConstants.AUCTION_END_CONSTRAINT_VALIDATION_MESSAGE;
import static ch6_hibernate.p75_bean_hibernate_validator.constants.HibernateValidationExampleConstants.NAME_CONSTRAINT_VALIDATION_MESSAGE;


@Slf4j
@Service
@RequiredArgsConstructor
public class HibernateValidationScheduler {

    private final ItemsRepository repository;
    private final ItemsService itemsService;

    @Scheduled(fixedDelay = 300000, initialDelay = 1000)
    public void process() {
        repository.deleteAll();

        try {
            // автовалидация при persist, спорный подход к архитектуре
            // работает при определенных зависимостях и их версиях
            itemsService.saveNewInvalidItem();
        } catch (Exception ex) {
            validateConstraintViolationException(
                    (ConstraintViolationException) ex.getCause().getCause());
        }

        // Более правильный путь - использовать javax.validation.Validator.validate()
        itemsService.validateAndSave();

        // Наконец, можно использовать сочетание @Validated и @Valid
        // Для контроллера @Validated не нужен
        try {
            Item item = new Item()
                    .setName(null)
                    .setAuctionEnd(LocalDate.now().minusDays(2));
            itemsService.validateWithAnnotationAndSave(item);
        } catch (ConstraintViolationException ex) {
            int k = 0;
            validateNullConstraintViolationException(ex);
        }

        log.info("HIBERNATE VALIDATOR TEST completed successfully");
    }

    void validateConstraintViolationException(ConstraintViolationException constraintViolationEx) {
        Assertions.assertEquals(2, constraintViolationEx.getConstraintViolations().size());
        constraintViolationEx.getConstraintViolations().forEach(cv ->
                Assertions.assertTrue(NAME_CONSTRAINT_VALIDATION_MESSAGE.equals(cv.getMessage())
                        || AUCTION_END_CONSTRAINT_VALIDATION_MESSAGE.equals(cv.getMessage())));
    }

    void validateNullConstraintViolationException(ConstraintViolationException constraintViolationEx) {
        Assertions.assertEquals(2, constraintViolationEx.getConstraintViolations().size());
        constraintViolationEx.getConstraintViolations().forEach(cv ->
                Assertions.assertTrue("Name should be not null.".equals(cv.getMessage())
                        || AUCTION_END_CONSTRAINT_VALIDATION_MESSAGE.equals(cv.getMessage())));
    }
}


