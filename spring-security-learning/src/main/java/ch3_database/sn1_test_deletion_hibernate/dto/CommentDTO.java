package ch3_database.sn1_test_deletion_hibernate.dto;

import lombok.*;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentDTO {
  private UUID id;

  private String externalId;

  private String content;

  private PostDTO post;
}
