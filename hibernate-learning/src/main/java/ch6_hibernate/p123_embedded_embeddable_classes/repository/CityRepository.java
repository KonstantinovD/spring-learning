package ch6_hibernate.p123_embedded_embeddable_classes.repository;

import ch6_hibernate.p123_embedded_embeddable_classes.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {

}
