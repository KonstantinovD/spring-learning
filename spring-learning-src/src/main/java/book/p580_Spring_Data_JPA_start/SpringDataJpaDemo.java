package book.p580_Spring_Data_JPA_start;

import book.p538_JPA_configuration_n_structure.helpers.EntityPrinterUtils;
import book.p580_Spring_Data_JPA_start.entity.Album;
import book.p580_Spring_Data_JPA_start.entity.Singer;
import book.p580_Spring_Data_JPA_start.repository.AlbumRepository;
import book.p580_Spring_Data_JPA_start.service.SpringDataJpaSingerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;

@Slf4j
@SpringBootApplication
public class SpringDataJpaDemo {
  public static void main(String[] args) {
    ConfigurableApplicationContext ctx =
        SpringApplication.run(SpringDataJpaDemo.class);
    SpringDataJpaSingerService singerService =
        ctx.getBean(SpringDataJpaSingerService.class);

    for (Singer s : singerService.findAll()) {
      log.info(s.toString());
    }
    EntityPrinterUtils.printSeparator();

    for (Singer s : singerService
        .findByFirstNameAndLastName("John", "Mayer")) {
      log.info(s.toString());
    }
    EntityPrinterUtils.printSeparator();

    AlbumRepository albumRepository =
        ctx.getBean(AlbumRepository.class);
    log.info("--- Print albums containing \"The\"");
    List<Album> albums = albumRepository.findByTitle("The");
    for (Album a : albums) {
      log.info(a.toString());
      log.info(a.getSinger().toString());
      log.info("---");
    }
    EntityPrinterUtils.printSeparator();

  }
}
