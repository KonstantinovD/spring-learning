package book.p490_start_with_hibernate.entity;


import lombok.Data;

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

  @ManyToMany(cascade = { CascadeType.ALL })
  @JoinTable(name = "singer instrument",
      joinColumns = @JoinColumn(name = "instrument_id"),
      inverseJoinColumns = @JoinColumn(name = "singer_id"))
  private Set<Singer> singers = new HashSet<>();
}
