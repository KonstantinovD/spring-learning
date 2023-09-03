# Spring-learning

This project is used for in-depth study of Spring Framework.

## Structure

### docs
Основное хранилище всех записей, ссылок и документирования.

```text
├── docs
│   ├── sh2_plain_java                         [некоторые примеры + вопросы с собесов]
│   │   │
│   │   ├── questions.adoc
│   │   ├── sn3_functional_interfaces.adoc
│   │   ├── sn9_concurrent.adoc
│   │
│   ├── sh3_database                           [заметки о БД]
│   │   ├── sn1_sharding.adoc
│   │
│   ├── ch4_architecture                       [общие записки об архитектуре приложений]
│   │   │
│   │   ├── system_design (system-design-primer, Alex Xu)
│   │   ├── ... (заметки из книги 
                 Microservices Patterns: With Examples in Java)
│   │
│   ├── ch5_spring_security_in_action          [Spring Security In Action]
│   │   ├── ...
│   │
│   ├── notes                                  [общие заметки]
│   │   │
│   │   ├── ... (Hibernate, ParallelStream, Generics, etc)
│   │   ├── ... links_to_resources.adoc
│   │   ├── ... VisualVM.adoc
│   │   ├── ... GLOBAL_NOTE.adoc (феномены чтения в БД, 
                                  Java memory model, 
                                  Hibernate transaction propagation)
│   │
│   ├── spring                                  [Книга Spring for Professionals + отдельные SNippets] 
│   │   ├── ...
```

### Кодовая база

```text
├── custom-spring-security-auth-server           [не работает]
│
├── protobuf-learning                            [пример protobuf, без документации]
│
├── spring-learning-src                          [Spring, plain Java]
│   │
│   ├── book (Книга 'Spring for Professionals' 
              и отдельные Snippets)
│   ├── ch2_plain_java (java заметки и примеры, 
                        большинство без доков)
│   ├── testing (мусор)
│   │
├── spring-security-learning                     [spring security & H2/hibernate]
│   │
│   ├── ch3_database (играем с H2 и hibernate/jpa)
│   ├── spring ('Spring for Professionals' примеры DB/SQL/JPA)
│   ├── ch5_spring_security_in_action 
        (Книга 'Spring Security In Action')
│
├── spring-security-oauth2-second-auth-server    [не работает]
│
├── src                                          [дубликат spring-learning-src]
```

### Кодовая база без документации - обзор

```text
├── ch2_plain_java
│   │
│   ├── sn1_fork_join_pool
│   ├── sn2_default_methods
│   ├── sn3_functional_interfaces
│   ├── sn4_threads_wait_notify
│   ├── sn5_locks_with_conditions
│   ├── sn6_constructors
│   ├── sn7_static_nested_classes_q26_oop
│   ├── sn8_reflection
│   ├── sn9_concurrent
│   │   ├── executorServiceExample/ExecutorServiceExample.java
│   │   │   ...
│   │   ├── ScheduledExecutorServiceExample.java
│   ├── sn10_stackowerflow
│   ├── sn11_generics
│   ├── sn12_strings
```

### Соответствующие ссылки на код
* sh2_plain_java -> [spring-learning-src/.../ch2_plain_java](spring-learning-src/src/main/java/ch2_plain_java)
* sh3_database -> [NO CODE]()
* ch4_architecture -> [NO CODE]()
* ch5_spring_security_in_action -> [spring-security-learning/.../ch5_spring_security_in_action](spring-security-learning/src/main/java/ch5_spring_security_in_action)
* notes -> [sn12_strings и sn11_generics](spring-learning-src/src/main/java/ch2_plain_java)
* spring -> [book](spring-learning-src/src/main/java/book) и [spring-security-learning/.../ch3_database](spring-security-learning/src/main/java/ch3_database)