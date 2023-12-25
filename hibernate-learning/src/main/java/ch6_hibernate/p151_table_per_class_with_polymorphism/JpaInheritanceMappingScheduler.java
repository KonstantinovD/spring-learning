package ch6_hibernate.p151_table_per_class_with_polymorphism;

import ch6_hibernate.p151_table_per_class_with_polymorphism.entity.BankAccount;
import ch6_hibernate.p151_table_per_class_with_polymorphism.entity.BillingDetails;
import ch6_hibernate.p151_table_per_class_with_polymorphism.entity.CreditCard;
import ch6_hibernate.p151_table_per_class_with_polymorphism.repository.BankAccountRepository;
import ch6_hibernate.p151_table_per_class_with_polymorphism.repository.CreditCardRepository;
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

        // owner переопределен как not-nullable в дочернем классе - мы обязаны его указать
        var bankAccount = new BankAccount().setAccount("account1").setBankName("SuperBank").setSwift("blocked");
        bankAccount.setOwner(OWNER_NAME); // nullable = false
        bankAccountRepository.save(bankAccount);

        var creditCard = new CreditCard().setCardNumber("1234_5555").setExpYear("26").setExpMonth("05");
        creditCard.setOwner(OWNER_NAME);
        creditCardRepository.save(creditCard);
        // делаем owner nullable
        var notOwnedCreditCard = new CreditCard().setCardNumber("6789_0000").setExpYear("27").setExpMonth("02");
        creditCardRepository.save(notOwnedCreditCard);

        // Данный код упадет с ошибкой 'BillingDetails is not mapped' - потому что BillingDetails - не @Entity
        // ---
        // List<BillingDetails> details = em.createQuery("from BillingDetails", BillingDetails.class).getResultList();
        // ---

        List<BankAccount> bankAccounts = bankAccountRepository.findAll();
        assertEquals(1, bankAccounts.size());
        List<CreditCard> creditCards = creditCardRepository.findAll();
        assertEquals(2, creditCards.size());

        // работает полиморфизм
        checkOwners(creditCards, true);
        checkOwners(bankAccounts, false);
    }

    private void checkOwners(List<? extends BillingDetails> billingDetailsList, boolean canBeNull) {
        for (BillingDetails billingDetails : billingDetailsList) {
            if (canBeNull) {
                assertTrue(billingDetails.getOwner() == null || OWNER_NAME.equals(billingDetails.getOwner()));
            } else {
                assertEquals(OWNER_NAME, billingDetails.getOwner());
            }
        }
    }

}
