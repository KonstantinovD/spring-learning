package ch5_spring_security_in_action.p74_more_about_UserDetailsService.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

// де-факто становится бином в ProjectConfig
public class InMemoryUserDetailsService implements UserDetailsService {

  // UserDetailsService управляет списком пользователей в памяти.
  private final List<UserDetails> users;

  public InMemoryUserDetailsService(List<UserDetails> users) {
    this.users = users;
  }

  @Override // фильтруем пользователя по его имени
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return users.stream()
        .filter(
            u -> u.getUsername().equals(username)
        ).findFirst().orElseThrow(() -> new UsernameNotFoundException("User not found"));
  }
}
