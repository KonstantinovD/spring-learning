package ch6_hibernate.p170_polymorphic_associations;

import ch6_hibernate.p170_polymorphic_associations.entity.BankAccount;
import ch6_hibernate.p170_polymorphic_associations.entity.BillingDetails;
import ch6_hibernate.p170_polymorphic_associations.entity.CreditCard;
import ch6_hibernate.p170_polymorphic_associations.entity.User;
import ch6_hibernate.p170_polymorphic_associations.repository.BankAccountRepository;
import ch6_hibernate.p170_polymorphic_associations.repository.CreditCardRepository;
import ch6_hibernate.p170_polymorphic_associations.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@Service
public class JpaPolymorphicAssociationsScheduler {

    private static final String OWNER_NAME = "VASILY";

    @Autowired
    private BankAccountRepository bankAccountRepository;
    @Autowired
    private CreditCardRepository creditCardRepository;
    @Autowired
    private UserRepository userRepository;

    // В основу JPA положен интерфейс EntityManager (see JPA structure)
    @PersistenceContext
    private EntityManager em;

    @Scheduled(fixedDelay = 60000, initialDelay = 1000)
    public void process() {
        bankAccountRepository.deleteAll();
        creditCardRepository.deleteAll();
        userRepository.deleteAll();

        var bankAccount = new BankAccount().setAccount("account1").setBankName("SuperBank").setSwift("blocked");
        bankAccount.setOwner(OWNER_NAME);
        var creditCard = new CreditCard().setCardNumber("1234_5555").setExpYear("26").setExpMonth("05");
        creditCard.setOwner(OWNER_NAME);

        User user = new User().setName(OWNER_NAME);
        user.addBillingDetail(bankAccount);
        user.addBillingDetail(creditCard);

        userRepository.save(user); // save user in table
        bankAccountRepository.save(bankAccount); // save bank account with filled in user_id field
        creditCardRepository.save(creditCard); // save credit card with filled in user_id field

        List<BillingDetails> details = em.createQuery("select bd from BillingDetails bd", BillingDetails.class)
                .getResultList();
        assertEquals(2, details.size());

        assertTrue(details.get(0) instanceof BankAccount);
        assertTrue(details.get(1) instanceof CreditCard);
        details.forEach(detail -> {
            assertEquals(user.getName(), detail.getUser().getName());
            assertEquals(user, detail.getUser());
        });

        var savedUser = userRepository.findAll().get(0);
        assertEquals(2, savedUser.getBillingDetails().size());
        assertEquals(1, savedUser.getBillingDetails()
                .stream().filter(
                        billingDetails ->
                                billingDetails instanceof BankAccount).count());
        assertEquals(1, savedUser.getBillingDetails()
                .stream().filter(
                        billingDetails ->
                                billingDetails instanceof CreditCard).count());
    }

}
