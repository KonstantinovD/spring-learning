package book.p490_start_with_hibernate.dao.impl;

import book.p490_start_with_hibernate.dao.SingerDao;
import book.p490_start_with_hibernate.entity.Singer;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Transactional
@Repository("singerDao")
public class SingerDaoImpl implements SingerDao {

  @Resource(name = "sessionFactory")
  private SessionFactory sessionFactory;

  // При взаимодействии с базой данных Hibernate работает с интерфейсом
  // Session, который получается из фабрики сеансов,
  // реализуемой в компоненте SessionFactory.
  @Transactional(readOnly = true)
  @Override
  public List<Singer> findAll() {
    return sessionFactory.getCurrentSession()
        .createQuery("from Singer s").list();
  }
}
