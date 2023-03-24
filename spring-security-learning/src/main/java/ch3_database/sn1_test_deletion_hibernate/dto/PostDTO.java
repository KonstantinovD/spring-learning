package ch3_database.sn1_test_deletion_hibernate.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostDTO {

  private UUID id;

  private String externalId;

  private String content;

  private Set<CommentDTO> comments;

}
