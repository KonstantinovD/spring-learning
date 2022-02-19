package book.p538_JPA_configuration_n_structure;

import book.p538_JPA_configuration_n_structure.config.JpaConfig;
import book.p538_JPA_configuration_n_structure.entity.Album;
import book.p538_JPA_configuration_n_structure.entity.Instrument;
import book.p538_JPA_configuration_n_structure.entity.Singer;
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
    call_listAll(singerService);
    call_listSingersWAnI(singerService);
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
      log.info(singer.toString());
      if (singer.getAlbums() != null) {
        for (Album album : singer.getAlbums()) {
          log.info("\t" + album.toString());
        }
      }
      if (singer.getInstruments() != null) {
        for (Instrument instrument : singer.getInstruments()) {
          log.info("\tlnstrument: " + instrument.getInstrumentId());
        }
      }
    }
  }
}
