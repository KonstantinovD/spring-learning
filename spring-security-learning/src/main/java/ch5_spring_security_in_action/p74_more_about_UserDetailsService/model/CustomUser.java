package ch5_spring_security_in_action.p74_more_about_UserDetailsService.model;

import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@AllArgsConstructor
public class CustomUser implements UserDetails {
  private final String username;
  private final String password;
  private final String authority;  // To make the example simple, a user has only one authority.

  // Returns a list containing only the GrantedAuthority object
  // with the name provided when you built the instance
  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return List.of(() -> authority);
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return username;
  }

  // Все 4 метода возвращают true, что означает, что
  // учетная запись не имеет срока действия и не может быть заблокирована.
  // Пользователь всегда активен и доступен для использования
  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
