package book.p490_start_with_hibernate.entity;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "instrument")
@Data
public class Instrument implements Serializable {
  @Id
  @Column(name = "instrument_id")
  private String instrumentId;
}
