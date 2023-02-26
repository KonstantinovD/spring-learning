package ch5_spring_security_in_action.p230_custom_csrf_token_in_db.csrf;

import ch5_spring_security_in_action.p230_custom_csrf_token_in_db.entity.Token;
import ch5_spring_security_in_action.p230_custom_csrf_token_in_db.repository.JpaTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.DefaultCsrfToken;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;
import java.util.UUID;

public class CustomCsrfTokenRepository implements CsrfTokenRepository {

  @Autowired
  private JpaTokenRepository jpaTokenRepository;

  @Override
  public CsrfToken generateToken(HttpServletRequest httpServletRequest) {
    String uuid = UUID.randomUUID().toString(); // генерируем токен
    // оставляем такие же headerName/parameterName как и в дефолтной реализации
    return new DefaultCsrfToken("X-CSRF-TOKEN", "_csrf", uuid);
  }

  @Override
  public void saveToken(CsrfToken csrfToken, HttpServletRequest httpServletRequest,
                        HttpServletResponse httpServletResponse) {
    String identifier = httpServletRequest.getHeader("X-IDENTIFIER");
    // ищем токен в базе по ID = "X-IDENTIFIER"
    Optional<Token> existingToken = jpaTokenRepository.findTokenByIdentifier(identifier);
    if (existingToken.isPresent()) { // токен найден
      Token tokenFromDb = existingToken.get();
      if (csrfToken != null && csrfToken.getToken() != null) {
        if (!csrfToken.getToken().equals(tokenFromDb.getToken())) {
          tokenFromDb.setToken(csrfToken.getToken()); // обновляем значение если токены разные
        } // по хорошему стоит удалять токен из базы если csrfToken == null
      }
    } else { // токен не нашли - создаем новую запись
      Token newToken = new Token();
      newToken.setToken(csrfToken.getToken());
      newToken.setIdentifier(identifier);
      jpaTokenRepository.save(newToken);
    }
  }

  @Override
  public CsrfToken loadToken(HttpServletRequest httpServletRequest) {
    String identifier = httpServletRequest.getHeader("X-IDENTIFIER");
    Optional<Token> existingToken = jpaTokenRepository.findTokenByIdentifier(identifier);
    // Ищет токен по "X-IDENTIFIER" из HttpServletRequest и загружает его, если он существует
    if (existingToken.isPresent()) {
      Token token = existingToken.get();
      return new DefaultCsrfToken("X-CSRF-TOKEN", "_csrf", token.getToken());
    }
    return null;
  }

}
