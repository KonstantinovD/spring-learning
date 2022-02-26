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
@NamedQueries({
    @NamedQuery(name = "Singer.findAllWithAlbum",
        query = "select distinct s from Singer s " +
            "left join fetch s.albums a"),
    @NamedQuery(name = "Singer.findAllWithAlbumAndInstrument",
        query = "select distinct s from Singer s " +
            "left join fetch s.albums a " +
            "left join fetch s.instruments i")
})
public class Singer implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Integer id;

  @Column(name = "first_name")
  private String firstName;

  @Column(name = "last_name")
  private String lastName;

  @Temporal(TemporalType.DATE)
  @Column(name = "birth_date")
  private Date birthDate;

  @EqualsAndHashCode.Exclude
  @ToString.Exclude
  @OneToMany(mappedBy = "singer", cascade = CascadeType.ALL,
      orphanRemoval = true)
  private Set<Album> albums = new HashSet<>();

  @EqualsAndHashCode.Exclude
  @ToString.Exclude
  @ManyToMany
  @JoinTable(name = "singer_instrument",
      joinColumns = @JoinColumn(name = "singer_id"),
      inverseJoinColumns = @JoinColumn(name = "instrument_id"))
  private Set<Instrument> instruments = new HashSet<>();

  @ToString.Exclude
  @Version
  private int version;
}
