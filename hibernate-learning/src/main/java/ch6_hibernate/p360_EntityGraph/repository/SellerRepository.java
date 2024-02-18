package ch6_hibernate.p360_EntityGraph.repository;

import ch6_hibernate.p360_EntityGraph.entity.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SellerRepository extends JpaRepository<Seller, Long> {

}
