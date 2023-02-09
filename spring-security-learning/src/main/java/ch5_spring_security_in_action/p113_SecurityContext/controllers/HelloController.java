package ch5_spring_security_in_action.p113_SecurityContext.controllers;

import ch5_spring_security_in_action.p113_SecurityContext.service.AsyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.concurrent.DelegatingSecurityContextCallable;
import org.springframework.security.concurrent.DelegatingSecurityContextExecutorService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.*;

@RestController
public class HelloController {

  @Autowired
  private AsyncService asyncService;

  @GetMapping("/hello")
  public String hello(Authentication a) {
    return "Hello, " + a.getName() + "!";
  }

  @GetMapping("/bye")
  public String goodbye() {

    Future<String> future = asyncService.getName();
    try {
      return "Hello, " + future.get() + "!";
    } catch (InterruptedException | ExecutionException ex) {
      return "Error while processing";
    }
  }

  @GetMapping("/ciao")
  public String ciao() throws Exception {
    Callable<String> task = () -> {
      SecurityContext context = SecurityContextHolder.getContext();
      return context.getAuthentication().getName();
    };

    ExecutorService e = Executors.newCachedThreadPool();
    try {
      var contextTask = new DelegatingSecurityContextCallable<>(task);
      return "Ciao, " + e.submit(contextTask).get() + "!";
    } finally {
      e.shutdown();
    }
  }

  @GetMapping("/hola")
  public String hola() throws Exception {
    Callable<String> task = () -> {
      SecurityContext context = SecurityContextHolder.getContext();
      return context.getAuthentication().getName();
    };

    ExecutorService e = Executors.newCachedThreadPool();
    e = new DelegatingSecurityContextExecutorService(e);
    try {
      return "Hola, " + e.submit(task).get() + "!";
    } finally {
      e.shutdown();
    }
  }
}
