package book.p580_Spring_Data_JPA_start.service;

import book.p580_Spring_Data_JPA_start.entity.Singer;
import book.p580_Spring_Data_JPA_start.repository.SingerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service("springDataJpaSingerService")
@Transactional
public class SpringDataJpaSingerServiceImpl implements SpringDataJpaSingerService {

  @Autowired
  SingerRepository singerRepository;

  @Transactional(readOnly = true)
  @Override
  public List<Singer> findAll() {
    return convertToList(singerRepository.findAll());
  }

  @Transactional(readOnly = true)
  @Override
  public List<Singer> findByFirstName(String firstName) {
    return singerRepository.findByFirstName(firstName);
  }

  @Transactional(readOnly = true)
  @Override
  public List<Singer> findByFirstNameAndLastName(String firstName, String lastName) {
    return singerRepository
        .findByFirstNameAndLastName(firstName, lastName);
  }

  private List<Singer> convertToList(Iterable<Singer> iterable) {
    List<Singer> result = new ArrayList<>();
    iterable.forEach(result::add);
    return result;
  }
}
