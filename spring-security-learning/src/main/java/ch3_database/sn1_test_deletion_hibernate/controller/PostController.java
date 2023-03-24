package ch3_database.sn1_test_deletion_hibernate.controller;

import ch3_database.sn1_test_deletion_hibernate.dto.CommentDTO;
import ch3_database.sn1_test_deletion_hibernate.dto.PostDTO;
import ch3_database.sn1_test_deletion_hibernate.entity.Comment;
import ch3_database.sn1_test_deletion_hibernate.entity.Post;
import ch3_database.sn1_test_deletion_hibernate.repository.CommentRepository;
import ch3_database.sn1_test_deletion_hibernate.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.UUID;

@RestController
public class PostController {

  @Autowired
  private PostRepository postRepository;

  @Autowired
  private CommentRepository commentRepository;

  @GetMapping("/createPost")
  public void createPost() {
    Post post = new Post();
    post.setId(UUID.randomUUID());
    post.setExternalId(UUID.randomUUID().toString());
    post.setContent("content_" + post.getExternalId());
//    postRepository.save(post);

    Comment comment = new Comment();
    comment.setId(UUID.randomUUID());
    comment.setExternalId(UUID.randomUUID().toString());
    comment.setContent("comment_" + comment.getExternalId());
//    comment.setPost(post);

    post.setComments(new HashSet<>());
    post.getComments().add(comment);


    // если сохранять и comment и post - через commentRepository.save(comment) - то проблем вообще не должно быть
    // только если мы начинаем сохранять только post и не делаем 'comment.setPost(post)' - получим exception в hibernate
    // либо делать 'comment.setPost(post)' но не добавлять comment в список
    // тогда exception не будет, но comment не сохранится
    //
    // а так можно и просто postRepository.save(post) вызвать, не сохраняя comment отдельно
    // если сделать comment.setPost(post)
    postRepository.save(post);
//    commentRepository.save(comment);

  }

  @GetMapping("/post/{id}")
  public PostDTO getPost(@PathVariable UUID id) {
    Post entity = postRepository.getById(id);

    PostDTO dto = PostDTO.builder()
        .id(entity.getId())
        .externalId(entity.getExternalId())
        .content(entity.getContent())
        .build();

    return dto;
  }

  @GetMapping("/comment/{id}")
  public CommentDTO getComment(@PathVariable UUID id) {
    Comment entity = commentRepository.getById(id);

    CommentDTO dto = CommentDTO.builder()
        .id(entity.getId())
        .externalId(entity.getExternalId())
        .content(entity.getContent())
        .build();

    return dto;
  }

}
