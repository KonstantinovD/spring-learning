package book.p490_start_with_hibernate.entity;

import lombok.*;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
//@Table(name = "SINGER")
@Data
public class Singer implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID")
  private Integer id;

  @Column(name = "first_name")
  private String firstName;

  @Column(name = "last_name")
  private String lastName;

  @Temporal(TemporalType.DATE)
  @Column(name = "birth_date")
  private Date birthDate;

  @OneToMany(mappedBy = "singer", cascade = CascadeType.ALL,
      orphanRemoval = true)
  private Set<Album> albums = new HashSet<>();

  @Version
  private int version;
}
