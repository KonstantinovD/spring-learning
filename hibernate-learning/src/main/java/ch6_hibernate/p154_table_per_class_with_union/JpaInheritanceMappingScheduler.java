package ch6_hibernate.p154_table_per_class_with_union;

import ch6_hibernate.p154_table_per_class_with_union.entity.BankAccount;
import ch6_hibernate.p154_table_per_class_with_union.entity.BillingDetails;
import ch6_hibernate.p154_table_per_class_with_union.entity.CreditCard;
import ch6_hibernate.p154_table_per_class_with_union.repository.BankAccountRepository;
import ch6_hibernate.p154_table_per_class_with_union.repository.CreditCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@Service
public class JpaInheritanceMappingScheduler {

    private static final String OWNER_NAME = "VASILY";

    @Autowired
    private BankAccountRepository bankAccountRepository;
    @Autowired
    private CreditCardRepository creditCardRepository;

    // В основу JPA положен интерфейс EntityManager (see JPA structure)
    @PersistenceContext
    private EntityManager em;

    @Scheduled(fixedDelay = 60000, initialDelay = 1000)
    public void process() {
        bankAccountRepository.deleteAll();
        creditCardRepository.deleteAll();

        var bankAccount = new BankAccount().setAccount("account1").setBankName("SuperBank").setSwift("blocked");
        bankAccount.setOwner(OWNER_NAME);
        bankAccountRepository.save(bankAccount);
        var creditCard = new CreditCard().setCardNumber("1234_5555").setExpYear("26").setExpMonth("05");
        creditCard.setOwner(OWNER_NAME);
        creditCardRepository.save(creditCard);

        List<BillingDetails> details = em.createQuery("select bd from BillingDetails bd", BillingDetails.class).getResultList();
        assertEquals(2, details.size());

        assertTrue(details.get(0) instanceof BankAccount);
        assertTrue(details.get(1) instanceof CreditCard);
    }

}
