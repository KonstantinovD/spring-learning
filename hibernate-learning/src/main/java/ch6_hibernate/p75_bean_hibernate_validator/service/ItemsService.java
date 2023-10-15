package ch6_hibernate.p75_bean_hibernate_validator.service;

import ch6_hibernate.p75_bean_hibernate_validator.Item;
import ch6_hibernate.p75_bean_hibernate_validator.repository.ItemsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.Validator;
import java.time.LocalDate;


@Slf4j
@Service
@Validated
@RequiredArgsConstructor
public class ItemsService {

    private final ItemsRepository repository;
    private final Validator validator;
    @Transactional
    public void saveNewInvalidItem() {
        repository.save(new Item()
                .setName("a")
                .setAuctionEnd(LocalDate.now()));
    }

    @Transactional
    public void validateAndSave() {
        Item item = new Item()
                .setName("b")
                .setAuctionEnd(LocalDate.now().minusDays(2));
        var constraintViolations = validator.validate(item);
        if (!CollectionUtils.isEmpty(constraintViolations)) {
            System.out.printf("Невалидный entity %s: %s \n", item, constraintViolations);
            return;
        }
        repository.save(item);
    }

    // @Validated - не получится, это class-level annotation, на методах работает только для validation groups
    // See https://reflectoring.io/bean-validation-with-spring-boot/#using-validation-groups-to-validate-objects-differently-for-different-use-cases
    @Transactional
    public void validateWithAnnotationAndSave(@Valid Item item) {
        repository.save(item);
    }

}
