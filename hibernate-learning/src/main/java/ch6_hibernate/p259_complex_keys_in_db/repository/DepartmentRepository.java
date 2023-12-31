package ch6_hibernate.p259_complex_keys_in_db.repository;

import ch6_hibernate.p259_complex_keys_in_db.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DepartmentRepository extends JpaRepository<Department, String> {

}
