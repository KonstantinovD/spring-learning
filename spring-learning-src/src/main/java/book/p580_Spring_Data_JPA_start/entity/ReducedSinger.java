package book.p580_Spring_Data_JPA_start.entity;

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
