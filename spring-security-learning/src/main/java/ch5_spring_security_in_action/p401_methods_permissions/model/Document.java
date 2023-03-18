package ch5_spring_security_in_action.p401_methods_permissions.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Document {
    @NonNull
    private String code;
    @NonNull
    private String owner;
}
