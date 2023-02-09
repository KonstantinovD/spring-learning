package ch5_spring_security_in_action.p113_SecurityContext.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class AsyncService {

  @Async
  public CompletableFuture<String> getName() {
    SecurityContext securityContext = SecurityContextHolder.getContext();
    String username = securityContext.getAuthentication().getName();
    return CompletableFuture.completedFuture(username);
  }

}
