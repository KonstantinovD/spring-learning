package ch6_hibernate.p999_presentation_sn1;

import ch6_hibernate.common.TransactionalProcessor;
import ch6_hibernate.p999_presentation_sn1.entity.Item;
import ch6_hibernate.p999_presentation_sn1.entity.Payment;
import ch6_hibernate.p999_presentation_sn1.repository.ItemRepository;
import ch6_hibernate.p999_presentation_sn1.repository.PaymentRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

import static ch6_hibernate.p999_presentation_sn1.dictionary.PaymentStatus.ERROR;


@Slf4j
@Service
@AllArgsConstructor
public class Presentation1Scheduler {

    private final ItemRepository itemRepository;
    private final TransactionalProcessor transactionalProcessor;
    private final PaymentRepository paymentRepository;


    // @Scheduled(fixedDelay = 60000, initialDelay = 1000)
    @Transactional
    public void process2() {
        String account = "account_1";
        log.info("METHOD EXECUTION STARTED");
        var oldPayment = paymentRepository.findByAccountNumberAndStatus(account, ERROR);
        var newPayment = new Payment().setAccountNumber(account).setAmount(oldPayment.getAmount());

        paymentRepository.delete(oldPayment);
        paymentRepository.flush();
        // ...
        paymentRepository.save(newPayment);
        // ...
        log.info("METHOD EXECUTION FINISHED");
    }
    // METHOD EXECUTION STARTED
    // Hibernate: select * from payments where account_number=? and status=?
    // Hibernate: call next value for s_payments
    // METHOD EXECUTION FINISHED
    // Hibernate: insert into payments (account_number, amount, status, id) values (?, ?, ?, ?)
    // Hibernate: delete from payments where id=?

    @Transactional
    public void createItems(List<String> names) {
        log.info("METHOD EXECUTION STARTED");
        var items = names.stream()
                .map(name -> new Item().setName(name)).toList();
        itemRepository.saveAll(items);
        log.info("METHOD EXECUTION FINISHED");
    }

    // METHOD EXECUTION STARTED
    // METHOD EXECUTION FINISHED
    // Hibernate: insert into payments (account_number, amount, status, id) values (?, ?, ?, ?)
    // Hibernate: delete from payments where id=?

    @Transactional
    public void process(List<Item> items,
            List<Payment> payments, int limit) {
        log.info("METHOD EXECUTION STARTED");
        for (int i = 0; i < limit; i++) {
            itemRepository.save(items.get(i));
            paymentRepository.save(payments.get(i));
        }
        log.info("METHOD EXECUTION FINISHED");
    }


    @Scheduled(fixedDelay = 60000, initialDelay = 1000)
    public void process1() {
        itemRepository.deleteAll();

        transactionalProcessor.runInNewTransaction(() -> {
            var item1 = new Item().setName("Паркет").setExternalId(UUID.randomUUID());
            // var item2 = new Item().setName("Плитка").setExternalId(UUID.randomUUID());;
            itemRepository.save(item1);
            // itemRepository.save(item2);
        });
        System.out.println("---------------------------------------");

        // var oldItem1 = itemRepository.findByName("Паркет");
        // var newItem1 = new Item().setName("Паркет");
        // itemRepository.delete(oldItem1);
        // itemRepository.save(newItem1);

        transactionalProcessor.runInNewTransaction(() -> {
            var oldItem2 = itemRepository.findByName("Паркет");
            var newItem2 = new Item().setName("Плитка").setExternalId(oldItem2.getExternalId());
            itemRepository.delete(oldItem2);
            itemRepository.flush();
            itemRepository.save(newItem2);
        });
        System.out.println("---------------------------------------");

        log.info("HIBERNATE TEST completed successfully");
    }

}
