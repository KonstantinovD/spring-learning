package ch5_spring_security_in_action.p387_pre_post_authorization.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
// Включаем в equals/hashCode только те поля,
// которые прямо выделены аннотацией @EqualsAndHashCode.Include
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Employee {

  @EqualsAndHashCode.Include
  private String name;
  private List<String> books; // не включаем в equals/hashCode
  @EqualsAndHashCode.Include
  private List<String> roles;

}
