package book.p580_Spring_Data_JPA_start.repository;

import book.p580_Spring_Data_JPA_start.entity.Album;
import book.p580_Spring_Data_JPA_start.entity.Singer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Long> {
  List<Album> findBySinger(Singer singer);

  @Query("select a from Album a where a.title like %:title%")
  List<Album> findByTitle(@Param("title") String t);
}
