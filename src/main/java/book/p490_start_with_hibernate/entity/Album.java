package book.p490_start_with_hibernate.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

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

  @ToString.Exclude
  @ManyToOne
  @JoinColumn(name = "singer_id")
  private Singer singer;

  @Version
  private int version;
}
