package book.p538_JPA_configuration_n_structure;

import book.p538_JPA_configuration_n_structure.config.JpaConfig;
import book.p538_JPA_configuration_n_structure.entity.Singer;
import book.p538_JPA_configuration_n_structure.helpers.EntityPrinterUtils;
import book.p538_JPA_configuration_n_structure.helpers.QueryHelper;
import book.p538_JPA_configuration_n_structure.service.SingerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;

@Slf4j
@SpringBootApplication
public class SpringJPADemo {
  public static void main(String[] args) {
    ConfigurableApplicationContext ctx =
        SpringApplication.run(JpaConfig.class);
    SingerService singerService = ctx.getBean(SingerService.class);
    QueryHelper queryHelper = ctx.getBean(QueryHelper.class);

    call_listAll(singerService);
    call_listSingersWAnI(singerService);
    call_findById(singerService);

    queryHelper.createUpdateDelete();
    queryHelper.checkNativeQuery();
  }

  private static void call_listAll(SingerService singerService) {
    List<Singer> singers = singerService.findAll();
    showSingers(singers);
  }

  private static void call_listSingersWAnI(SingerService singerService) {
    List<Singer> singers =
        singerService.listSingersWithAlbumAndInstrument();
    showSingersWithAlbumAndInstrument(singers);
  }

  private static void call_findById(SingerService singerService) {
    Singer singer = singerService.findById(2);
    log.info(" ---- Show singer by id:");
    EntityPrinterUtils.showFetchSingerInfo(singer);
  }

  private static void showSingers(List<Singer> singers) {
    log.info(" ---- Listing singers:");
    for (Singer singer : singers) {
      log.info(singer.toString());
    }
  }

  private static void showSingersWithAlbumAndInstrument(
      List<Singer> singers) {
    log.info(" ---- Listing singers with instruments:");
    for (Singer singer : singers) {
      EntityPrinterUtils.showFetchSingerInfo(singer);
    }
  }
}
