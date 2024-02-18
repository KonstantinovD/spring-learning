package ch6_hibernate.p361_SqlResultSetMapping.dto;

import ch6_hibernate.p361_SqlResultSetMapping.dictionary.AttributeCategory;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.UUID;


@Getter
@Setter
@ToString
@NoArgsConstructor
public class ItemAttributeDto {

    public ItemAttributeDto(UUID itemId, LocalDate itemDate,
            String code, String value, String category) {
        this.itemId = itemId;
        this.itemDate = itemDate;
        this.code = code;
        this.value = value;
        this.category = AttributeCategory.valueOf(category);
    }

    private UUID itemId;
    private LocalDate itemDate;
    private String code;
    private String value;
    private AttributeCategory category;
}
