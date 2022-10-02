package book.p580_Spring_Data_JPA_start.repository;

import book.p580_Spring_Data_JPA_start.entity.Singer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SingerRepository
    extends CrudRepository<Singer, Integer> {
  List<Singer> findByFirstName(String firstName);
  List<Singer> findByFirstNameAndLastName(
      String firstName, String lastName);
}
