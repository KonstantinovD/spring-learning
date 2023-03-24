package ch3_database.sn1_test_deletion_hibernate.repository;

import ch3_database.sn1_test_deletion_hibernate.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import java.util.UUID;

public interface CommentRepository extends JpaRepository<Comment, UUID> {

  Comment findCommentById(UUID id);

  @Modifying
  void deleteById(UUID id);
}

