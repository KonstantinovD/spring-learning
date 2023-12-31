package ch6_hibernate.p259_complex_keys_in_db.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;


@Data
@Entity
@Table(name = "departments")
@Accessors(chain = true)
public class Department {

    @Id
    @NotNull
    private String id;

    @NotNull
    private String name;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(mappedBy = "department", fetch = FetchType.LAZY)
    private List<User> users = new ArrayList<>();

}
