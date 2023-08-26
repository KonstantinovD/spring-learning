package book.p580_Spring_Data_JPA_start.service;


import book.p580_Spring_Data_JPA_start.entity.Singer;

import java.util.List;

public interface SpringDataJpaSingerService {
  List<Singer> findAll();
  List<Singer> findByFirstName(String firstName);
  List<Singer> findByFirstNameAndLastName(
      String firstName, String lastName);
}
