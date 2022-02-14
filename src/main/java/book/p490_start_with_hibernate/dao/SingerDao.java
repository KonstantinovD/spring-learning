package book.p490_start_with_hibernate.dao;

import book.p490_start_with_hibernate.entity.Singer;

import java.util.List;

public interface SingerDao {
  List<Singer> findAll();
}
