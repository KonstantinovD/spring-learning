package ch5_spring_security_in_action.p230_custom_csrf_token_in_db.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class Token {

  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  private int id;

  private String identifier;
  private String token;

}
