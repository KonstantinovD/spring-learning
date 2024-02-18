package ch6_hibernate.p361_SqlResultSetMapping.entity.id;

import lombok.Data;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;


@Data
@Embeddable
public class ItemId implements Serializable {

    private UUID externalId;
    private LocalDate itemDate;

}
