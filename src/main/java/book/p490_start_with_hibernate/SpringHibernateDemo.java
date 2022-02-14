package book.p490_start_with_hibernate;

import book.p490_start_with_hibernate.dao.SingerDao;
import book.p490_start_with_hibernate.entity.Singer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;

@Slf4j
@SpringBootApplication
public class SpringHibernateDemo {

  public static void main(String[] args) {
    ConfigurableApplicationContext ctx =
        SpringApplication.run(SpringHibernateDemo.class);

    SingerDao singerDao = ctx.getBean(SingerDao.class);
    listSingers(singerDao.findAll());

    ctx.close();
  }

  private static void listSingers(List<Singer> singers) {
    log.info(" ---- Listing singers:");
    for (Singer singer : singers) {
      log.info(singer.toString());
    }
  }
}
