package ch3_database.sn1_test_deletion_hibernate.repository;

import ch3_database.sn1_test_deletion_hibernate.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PostRepository extends JpaRepository<Post, UUID> {

  Post findPostById(UUID id);

  void delete(Post entity);
}
