package ch6_hibernate.p259_complex_keys_in_db.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serializable;


@Data
@Entity
@Table(name = "users")
@IdClass(User.UserId.class)
@Accessors(chain = true)
public class User {

    @Id
    @NotNull
    private String username;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    private Department department;

    private String employeeType;

    @AllArgsConstructor
    @NoArgsConstructor
    public static class UserId implements Serializable {

        private String username;
        private String department;

    }

}
