package ch6_hibernate.p259_complex_keys_in_db;

import ch6_hibernate.common.TransactionalProcessor;
import ch6_hibernate.p259_complex_keys_in_db.entity.Department;
import ch6_hibernate.p259_complex_keys_in_db.entity.User;
import ch6_hibernate.p259_complex_keys_in_db.repository.DepartmentRepository;
import ch6_hibernate.p259_complex_keys_in_db.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;


@Slf4j
@Service
@AllArgsConstructor
public class ComplexKeysInDBScheduler {

    private static final String USERNAME = "Nikolay";
    private static final String DEPARTMENT_ID = "1781157";
    private static final String DEPARTMENT_NAME = "R&D";
    private static final String EMPLOYEE_TYPE = "Developer";

    private final UserRepository userRepository;
    private final TransactionalProcessor transactionalProcessor;
    private final DepartmentRepository departmentRepository;
    private final JdbcTemplate jdbcTemplate;

    @Scheduled(fixedDelay = 60000, initialDelay = 1000)
    public void process() {
        userRepository.deleteAll();
        departmentRepository.deleteAll();

        Department department = new Department().setId(DEPARTMENT_ID).setName(DEPARTMENT_NAME);
        departmentRepository.save(department);

        transactionalProcessor.runInNewTransaction(() -> {
            var dep = departmentRepository.findAll().get(0);
            User u = new User().setUsername(USERNAME)
                    .setDepartment(dep).setEmployeeType(EMPLOYEE_TYPE);
            userRepository.saveAndFlush(u);
        });

        transactionalProcessor.runInNewTransaction(() -> {
            Department savedDepartment = departmentRepository.getById(department.getId());
            assertEquals(savedDepartment.getId(), DEPARTMENT_ID);
            assertEquals(savedDepartment.getName(), DEPARTMENT_NAME);
            assertEquals(savedDepartment.getUsers().size(), 1);

            var user = savedDepartment.getUsers().get(0);
            assertEquals(user.getUsername(), USERNAME);
            assertEquals(user.getEmployeeType(), EMPLOYEE_TYPE);
        });

        transactionalProcessor.runInNewTransaction(() -> {
            User savedUser = userRepository.getById(new User.UserId(USERNAME, DEPARTMENT_ID));
            assertEquals(savedUser.getUsername(), USERNAME);
            assertEquals(savedUser.getDepartment().getId(), DEPARTMENT_ID);
            assertEquals(savedUser.getDepartment().getName(), DEPARTMENT_NAME);
            assertEquals(savedUser.getEmployeeType(), EMPLOYEE_TYPE);
        });

        List<Map<String, Object>> usersInDb = jdbcTemplate.queryForList("select * from users");
        List<Map<String, Object>> departmentsInDb = jdbcTemplate.queryForList("select * from departments");
        checkUsers(usersInDb);
        checkDepartments(departmentsInDb);

        log.info("HIBERNATE TEST completed successfully");
    }

    void checkUsers(List<Map<String, Object>> usersInDb) {
        assertEquals(1, usersInDb.size());
        var userInDb = usersInDb.get(0);
        assertEquals(userInDb.get("USERNAME"), USERNAME);
        assertEquals(userInDb.get("DEPARTMENT_ID"), DEPARTMENT_ID);
        assertEquals(userInDb.get("EMPLOYEE_TYPE"), EMPLOYEE_TYPE);
    }

    void checkDepartments(List<Map<String, Object>> departmentsInDb) {
        assertEquals(1, departmentsInDb.size());
        var departmentInDb = departmentsInDb.get(0);
        assertEquals(departmentInDb.get("ID"), DEPARTMENT_ID);
        assertEquals(departmentInDb.get("NAME"), DEPARTMENT_NAME);
    }

}
