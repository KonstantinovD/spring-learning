package ch6_hibernate.p999_presentation_sn1.repository;

import ch6_hibernate.p999_presentation_sn1.dictionary.PaymentStatus;
import ch6_hibernate.p999_presentation_sn1.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PaymentRepository extends JpaRepository<Payment, Long> {

    Payment findByAccountNumberAndStatus(String account, PaymentStatus status);

}
