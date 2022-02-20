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
import javax.persistence.criteria.*;
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

  @Transactional(readOnly = true)
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

    log.info(
        "Singer with id: {} deleted successfully", singer.getId());
  }

  @Transactional(readOnly = true)
  @Override
  public List<Singer> findSingersByNativeQuery() {
    return em.createNativeQuery(
        ALL_SINGER_NATIVE_QUERY, "singerResult").getResultList();
  }

  @Transactional(readOnly = true)
  @Override
  public List<Object[]> findObjectsByNativeQuery() {
    return em.createNativeQuery(ALL_SINGER_NATIVE_QUERY,
        "singerResult2").getResultList();
  }

  @Transactional(readOnly = true)
  @Override
  public List<ReducedSinger> findReducesSingersByNativeQuery() {
    return em.createNativeQuery(REDUCED_SINGER_NATIVE_QUERY,
        "singerResult3").getResultList();
  }

  @Transactional(readOnly = true)
  @Override
  public List<Singer> findByCriteriaQuery(String firstName, String lastName) {
    log.info("Finding singer for firstName: {} and lastName: {}",
        firstName, lastName);
    CriteriaBuilder cb = em.getCriteriaBuilder(); // билдер
    // создается типизированный запрос
    CriteriaQuery<Singer> criteriaQuery =
        cb.createQuery(Singer.class);

    // корневой объект запроса, представляющий интерфейс
    // Root<Singer>. Этот объект соответствует указанной сущности и
    // формирует основу для path-выражений в запросе.
    Root<Singer> singerRoot = criteriaQuery.from(Singer.class);
    // equal to JPQL 'left join fetch'
    singerRoot.fetch("albums", JoinType.LEFT);
    singerRoot.fetch("instruments", JoinType.LEFT);
    criteriaQuery.select(singerRoot).distinct(true);

    Predicate resultPredicate = cb.conjunction(); // объединение
    if (firstName != null) {
      Predicate predicateFirstName =
          cb.equal(singerRoot.get("firstName"), firstName);
      resultPredicate = cb.and(resultPredicate, predicateFirstName);
    }
    if (lastName != null) {
      Predicate predicateLastName =
          cb.equal(singerRoot.get("lastName"), lastName);
      resultPredicate = cb.and(resultPredicate, predicateLastName);
    }
    criteriaQuery.where(resultPredicate);

    return em.createQuery(criteriaQuery).getResultList();
  }
}
