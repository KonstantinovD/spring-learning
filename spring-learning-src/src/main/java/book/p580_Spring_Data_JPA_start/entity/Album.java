package book.p580_Spring_Data_JPA_start.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "album")
@Data
public class Album implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "title")
  private String title;

  @Temporal(TemporalType.DATE)
  @Column(name = "release_date")
  private Date releaseDate;

  @EqualsAndHashCode.Exclude
  @ToString.Exclude
  @ManyToOne
  @JoinColumn(name = "singer_id")
  private Singer singer;

  @Version
  private int version;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Album album = (Album) o;
    return id.equals(album.id) && title.equals(album.title);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, title);
  }
}
