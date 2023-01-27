package ch5_spring_security_in_action.p53_customAuthenticationProvider.security;

import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {

    // The getName() method is inherited by Authentication from the Principal interface.
    String username = authentication.getName();
    String password = String.valueOf(authentication.getCredentials());

    // данное условие в дефолтной реализации вызывает UserDetailsService и PasswordEncoder
    if ("john".equals(username) && "pass3".equals(password)) {
      return new UsernamePasswordAuthenticationToken(username, password, Collections.emptyList());
    } else {
      throw new AuthenticationCredentialsNotFoundException("Error in authentication!");
    }
  }

  public boolean supports(Class<?> authenticationType) {
    return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authenticationType);
  }
}
