package book.p538_JPA_configuration_n_structure.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;


@AllArgsConstructor
@Data
public class ReducedSinger {
  private String firstName;

  private String lastName;

  private Date birthDate;
}
