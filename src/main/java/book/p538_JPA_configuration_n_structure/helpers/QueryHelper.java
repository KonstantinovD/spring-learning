package book.p538_JPA_configuration_n_structure.helpers;

import book.p538_JPA_configuration_n_structure.entity.Album;
import book.p538_JPA_configuration_n_structure.entity.Singer;
import book.p538_JPA_configuration_n_structure.service.SingerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Slf4j
@Component
@AllArgsConstructor
public class QueryHelper {

  @Autowired
  SingerService singerService;

  public void createUpdateDelete() {
    Singer s = insert();
    s = update(s);
    delete(s);
  }

  public Singer insert() {
    Singer singer = new Singer();
    singer.setFirstName("BB");
    singer.setLastName("King");
    singer.setBirthDate(new Date(new GregorianCalendar(
        1940, Calendar.AUGUST, 16)
        .getTime().getTime()));

    Album album1 = new Album();
    album1.setTitle("My Kind of Blues");
    album1.setReleaseDate(new java.sql.Date(new GregorianCalendar(
        1961, Calendar.JULY, 18)
        .getTime().getTime()));

    Album album2 = new Album();
    album2.setTitle("A Heart Full of Blues");
    album2.setReleaseDate(new java.sql.Date(new GregorianCalendar(
        1962, Calendar.MARCH, 20)
        .getTime().getTime()));

    singer.getAlbums().add(album1);
    // После добавления зависимого объекта в список главного
    // (one-to-many), надо установить главный объект в соответствующее
    // поле зависимого объекта. В противном случае зависимый объект
    // (Album) не сможет сохраниться и упадет с "singer_id is null"
    album1.setSinger(singer);
    singer.getAlbums().add(album2);
    album2.setSinger(singer);

    singerService.save(singer);

    EntityPrinterUtils.showFetchSingerInfo(singer);

    return singer;
  }

  public Singer update(Singer singer) {
    int id = singer.getId();

    singer.setFirstName("Karol");

    // удаление альбома через удаление из сета фиг настроишь
    /*
    Optional<Album> album = singer.getAlbums().stream().filter(
        alb -> "A Heart Full of Blues".equals(alb.getTitle()))
        .findFirst();
    singer.getAlbums().remove(album.get());

    singer.getAlbums().removeIf(album1 -> album1.equals("A Heart Full of Blues"));
//    album.get().setSinger(null);
//    album.ifPresent(value -> singer.getAlbums().remove(value));
     */

    singerService.save(singer);

    Singer updated = singerService.findById(id);
    EntityPrinterUtils.showFetchSingerInfo(updated);
    return updated;
  }

  public void delete(Singer singer) {
    singerService.delete(singer);
  }

}
