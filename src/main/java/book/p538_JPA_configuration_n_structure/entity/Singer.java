package book.p538_JPA_configuration_n_structure.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
//@Table(name = "SINGER")
@Data
@NamedQueries({
    @NamedQuery(name = Singer.FIND_ALL,
        query = "select s from Singer s"),
    @NamedQuery(name = Singer.FIND_SINGER_BY_ID,
        query = "select distinct s from Singer s "
            + "left join fetch s.albums a "
            + "left join fetch s.instruments i "
            + "where s.id = :id"),
    @NamedQuery(name = Singer.FIND_ALL_WITH_ALBUM,
        query = "select distinct s from Singer s " +
            "left join fetch s.albums a " +
            "left join fetch s.instruments i")
})
public class Singer implements Serializable {

  public static final String FIND_ALL = "Singer.findAll";
  public static final String FIND_SINGER_BY_ID = "Singer.findById";
  public static final String FIND_ALL_WITH_ALBUM =
      "Singer.findAllWithAlbum";
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
  @ManyToMany(cascade = { CascadeType.ALL })
  @JoinTable(name = "singer_instrument",
      joinColumns = @JoinColumn(name = "singer_id"),
      inverseJoinColumns = @JoinColumn(name = "instrument_id"))
  private Set<Instrument> instruments = new HashSet<>();

  @ToString.Exclude
  @Version
  private int version;
}
