package ch3_database.sn1_test_deletion_hibernate.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "post")
public class Post {

  @Id
  private UUID id;

  private String externalId;

  private String content;

  @EqualsAndHashCode.Exclude
  @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
  @BatchSize(size = 5)
  private Set<Comment> comments;

  @Override
  public String toString() {
    return "Post{" +
        "id=" + id + ", externalId=" + externalId +
        ", content='" + content  + '}';
  }
}
