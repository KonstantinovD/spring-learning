package book.p538_JPA_configuration_n_structure.service;

import book.p538_JPA_configuration_n_structure.entity.ReducedSinger;
import book.p538_JPA_configuration_n_structure.entity.Singer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Slf4j
@Service("jpaSingerService")
@Repository
@Transactional
public class SingerServiceImpl implements SingerService {
  public static final String ALL_SINGER_NATIVE_QUERY =
      "select id, first_name, last_name, birth_date, version from singer";
  public static final String REDUCED_SINGER_NATIVE_QUERY =
      "select first_name, last_name, birth_date from singer";

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

  @Override
  public Singer findById(Integer id) {
    TypedQuery<Singer> query = em.createNamedQuery(
        Singer.FIND_SINGER_BY_ID, Singer.class);
    query.setParameter("id", id);
    return query.getSingleResult();
  }

  @Override
  public Singer save(Singer singer) {
    if (singer.getId() == null) {
      log.info("Inserting new Singer");
      em.persist(singer);
    } else {
      em.merge(singer);
      log.info("Updating existing singer");
    }
    log.info("Singer saved with id: " + singer.getId());
    return singer;
  }

  @Override
  public void delete(Singer singer) {
    Singer mergedSinger = em.merge(singer);
    em.remove(mergedSinger);

    log.info("Singer with id: " + singer.getId()
        + "deleted successfully");
  }

  @Override
  public List<Singer> findSingersByNativeQuery() {
    return em.createNativeQuery(
        ALL_SINGER_NATIVE_QUERY, "singerResult").getResultList();
  }

  @Override
  public List<Object[]> findObjectsByNativeQuery() {
    return em.createNativeQuery(ALL_SINGER_NATIVE_QUERY,
        "singerResult2").getResultList();
  }

  @Override
  public List<ReducedSinger> findReducesSingersByNativeQuery() {
    return em.createNativeQuery(REDUCED_SINGER_NATIVE_QUERY,
        "singerResult3").getResultList();
  }
}
