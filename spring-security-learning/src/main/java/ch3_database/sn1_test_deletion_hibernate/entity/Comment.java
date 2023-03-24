package ch3_database.sn1_test_deletion_hibernate.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "comment")
public class Comment {

  @Id
  private UUID id;

  private String externalId;

  private String content;

  @EqualsAndHashCode.Exclude
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "post_id", nullable = false)
  private Post post;

  @Override
  public String toString() {
    return "Comment{" +
        "id=" + id + ", externalId=" + externalId +
        ", content='" + content  + '}';
  }
}
