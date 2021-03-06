package book.p580_Spring_Data_JPA_start.entity;


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
  @ManyToMany
  @JoinTable(name = "singer_instrument",
      joinColumns = @JoinColumn(name = "instrument_id"),
      inverseJoinColumns = @JoinColumn(name = "singer_id"))
  private Set<Singer> singers = new HashSet<>();
}
