package ch6_hibernate.p65_presisted_object_and_hibernate_properties;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.BatchSize;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;


@Entity
@Table(name = "users")
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "s_users")
    @SequenceGenerator(name = "s_users", sequenceName = "s_users", allocationSize = 1)
    @Getter(AccessLevel.PROTECTED) // FYI, ломбоковские getter/setter
    @Setter(AccessLevel.PROTECTED) // можно снабжать модификаторами доступа
    private BigDecimal id;

    @Transient // иначе hibernate ругается 'Столбец "USER0_.FIRST_NAME" не найден'
    private String firstName;

    @Transient // иначе hibernate ругается 'Столбец "USER0_.LAST_NAME" не найден'
    private String lastName;

    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @BatchSize(size = 5)
    private List<Note> notes;

    @Access(AccessType.PROPERTY) // иначе не работает
    public String getName() {
        return firstName + ' ' + lastName;
    }

    @Access(AccessType.PROPERTY) // иначе не работает
    public void setName(String name) {
        StringTokenizer t = new StringTokenizer(name);
        firstName = t.nextToken();
        lastName = t.nextToken();
    }

    public void addNote(String content) {
        if (notes == null) {
            notes = new ArrayList<>();
        }
        Note note = new Note();
        note.setText(content);
        note.setUser(this);
        notes.add(note);
    }
}
