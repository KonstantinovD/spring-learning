package ch6_hibernate.sn002_spring_cache.repository;

import ch6_hibernate.sn002_spring_cache.entity.TestNote;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


// @Repository
@CacheConfig(cacheNames = "myCache")
public interface TestNoteRepository extends JpaRepository<TestNote, Long> {

    @Cacheable
    List<TestNote> findAll();

    // метод очистки кеша, allEntries -> все сущности
    @CacheEvict(allEntries = true)
    default void evictTestCache() {}

}
