package book.p538_JPA_configuration_n_structure.helpers;

import book.p538_JPA_configuration_n_structure.entity.Album;
import book.p538_JPA_configuration_n_structure.entity.Instrument;
import book.p538_JPA_configuration_n_structure.entity.Singer;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EntityPrinterUtils {

  public static void showFetchSingerInfo(Singer singer) {
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

  public static void printSeparator() {
    log.info("--------------------------------");
  }

}
