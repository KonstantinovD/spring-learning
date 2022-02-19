package book.p538_JPA_configuration_n_structure.service;

import book.p538_JPA_configuration_n_structure.entity.Singer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Slf4j
@Service("jpaSingerService")
@Repository
@Transactional
public class SingerServiceImpl implements SingerService {
  public static final String ALL_SINGER_NATIVE_QUERY =
      "select id, first_name, last_name, birth_date from singer";

  // В основу JPA положен интерфейс EntityManager (see JPA structure)
  @PersistenceContext
  private EntityManager em;

  @Transactional(readOnly = true)
  @Override
  public List<Singer> findAll() {
    return em.createNamedQuery(Singer.FIND_ALL, Singer.class)
        .getResultList();
  }

  @Transactional(readOnly = true)
  @Override
  public List<Singer> listSingersWithAlbumAndInstrument() {
    return em.createNamedQuery(
        Singer.FIND_ALL_WITH_ALBUM, Singer.class).getResultList();
  }


}
