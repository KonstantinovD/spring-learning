package ch6_hibernate.p378_interceptors.repository;

import ch6_hibernate.p378_interceptors.entity.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SellerRepository extends JpaRepository<Seller, Long> {

}
