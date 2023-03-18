package ch5_spring_security_in_action.p387_pre_post_authorization.service;

import ch5_spring_security_in_action.p387_pre_post_authorization.model.Employee;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BookService {

  private Map<String, Employee> records =
      Map.of("emma",
          new Employee("Emma Thompson",
              List.of("Karamazov Brothers"),
              List.of("accountant", "reader")),
          "natalie",
          new Employee("Natalie Parker",
              List.of("Beautiful Paris"),
              List.of("researcher")));

  // проверяем что объект 'Employee' содержит роль 'reader' в списке Employee.roles
  @PostAuthorize("returnObject.roles.contains('reader')")
  public Employee getBookDetails(String name) {
    return records.get(name);
  }

}
