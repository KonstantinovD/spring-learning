package ch6_hibernate.p112_mapping_value_types;

import ch6_hibernate.p112_mapping_value_types.dictionary.PersonalItemRarity;
import ch6_hibernate.p112_mapping_value_types.dictionary.PersonalItemType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;


@NoArgsConstructor
@Entity
@Table(name = "personal_items")
@Getter
@Setter
@Accessors(chain = true)
public class PersonalItem {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "s_personal_items")
    @SequenceGenerator(name = "s_personal_items", sequenceName = "s_personal_items", allocationSize = 1)
    private BigDecimal id;

    @NotNull
    @Enumerated(EnumType.ORDINAL)
    private PersonalItemType type;

    @NotNull
    @Enumerated(EnumType.STRING)
    private PersonalItemRarity rarity;

}
