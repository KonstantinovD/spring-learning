package ch5_spring_security_in_action.p102_Implementing_authentication.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

//@Component - закомменчено во избежание Circular Dependency.
public class CustomAuthenticationProvider implements AuthenticationProvider {

  // Если мы ничего не настраиваем на уровне фильтра аутентификации, то стандартный запрос аутентификации
  // с именем пользователя и паролем представляется с помощью UsernamePasswordAuthenticationToken
  @Override
  public boolean supports(Class<?> authenticationClass) {
    return authenticationClass.equals(UsernamePasswordAuthenticationToken.class);
  }

  private UserDetailsService userDetailsService;
  private PasswordEncoder passwordEncoder;

  @Autowired // избегаем Circular Dependency
  public CustomAuthenticationProvider(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
    this.userDetailsService = userDetailsService;
    this.passwordEncoder = passwordEncoder;
  }

// Кстати, избежать Circular Dependency можно и через autowired сеттеры, а не через конструктор - тоже будет работать
//
//  @Autowired
//  public void setUserDetailsService(UserDetailsService userDetailsService) {
//    this.userDetailsService = userDetailsService;
//  }
//  @Autowired
//  public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
//    this.passwordEncoder = passwordEncoder;
//  }

  // делегируем всю работу UserDetailsService/PasswordEncoder как обычный AuthenticationProvider
  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    String username = authentication.getName(); // метод из AbstractAuthenticationToken
    String password = authentication.getCredentials().toString(); // метод из UsernamePasswordAuthenticationToken

    UserDetails u = userDetailsService.loadUserByUsername(username);

    if (passwordEncoder.matches(password, u.getPassword())) {
      // Если пароль совпадает, возвращает имплементацию Authentication с необходимыми данными
      return new UsernamePasswordAuthenticationToken(username, password, u.getAuthorities());
    } else {
      throw new BadCredentialsException("Something went wrong!");
    }
  }
}
