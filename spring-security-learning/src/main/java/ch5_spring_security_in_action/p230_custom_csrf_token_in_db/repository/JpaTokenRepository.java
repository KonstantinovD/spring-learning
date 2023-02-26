package ch5_spring_security_in_action.p230_custom_csrf_token_in_db.repository;

import ch5_spring_security_in_action.p230_custom_csrf_token_in_db.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import javax.persistence.Table;
import java.util.Optional;

@Table(name = "token")
public interface JpaTokenRepository extends JpaRepository<Token, Integer> {

  Optional<Token> findTokenByIdentifier(String identifier);

  void delete(@NonNull Token entity);
}
