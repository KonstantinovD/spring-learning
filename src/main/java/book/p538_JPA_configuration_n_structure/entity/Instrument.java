package book.p538_JPA_configuration_n_structure.entity;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "instrument")
@Data
public class Instrument implements Serializable {
  @Id
  @Column(name = "instrument_id")
  private String instrumentId;

  @EqualsAndHashCode.Exclude
  @ToString.Exclude
  @ManyToMany(cascade = { CascadeType.ALL })
  @JoinTable(name = "singer_instrument",
      joinColumns = @JoinColumn(name = "instrument_id"),
      inverseJoinColumns = @JoinColumn(name = "singer_id"))
  private Set<Singer> singers = new HashSet<>();
}
