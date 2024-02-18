package ch6_hibernate.p361_SqlResultSetMapping.entity.id;

import ch6_hibernate.p361_SqlResultSetMapping.entity.Item;
import lombok.Data;

import javax.persistence.Embeddable;
import java.io.Serializable;


@Data
@Embeddable
public class ItemAttributeId implements Serializable {

    private Item item;
    private String code;

}
