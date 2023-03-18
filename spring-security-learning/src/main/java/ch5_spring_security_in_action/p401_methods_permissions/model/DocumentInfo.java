package ch5_spring_security_in_action.p401_methods_permissions.model;

import lombok.*;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class DocumentInfo extends Document {

  private String extension;

  // lombok cannot call super constructor with params
  public DocumentInfo(@NonNull String code, @NonNull String owner, String extension) {
    super(code, owner);
    this.extension = extension;
  }
}
