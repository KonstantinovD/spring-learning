package ch5_spring_security_in_action.p387_pre_post_authorization.service;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class NameService {

  private final Map<String, List<String>> secretNames =
      Map.of(
          "natalie", List.of("Energico", "Perfecto"),
          "emma", List.of("Fantastico"));

  // Only users having 'write' authority can call the method
  @PreAuthorize("hasAuthority('write')")
  public String getName() {
    return "Fantastico";
  }
  // User can get access only to values by its username
  @PreAuthorize("#name == authentication.principal.username")
  public List<String> getSecretNames(String name) {
    return secretNames.get(name);
  }
}
