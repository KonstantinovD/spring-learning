package book.p538_JPA_configuration_n_structure.service;

import book.p538_JPA_configuration_n_structure.entity.Singer;

import java.util.List;

public interface SingerService {
  List<Singer> findAll();
  List<Singer> listSingersWithAlbumAndInstrument();
  Singer findById(Integer id);
  Singer save(Singer singer);
  void delete(Singer singer);
//  List<Singer> findAllByNativeQuery();
}
