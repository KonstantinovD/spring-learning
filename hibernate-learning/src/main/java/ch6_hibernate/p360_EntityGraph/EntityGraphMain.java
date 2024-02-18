package ch6_hibernate.p360_EntityGraph;

import ch6_hibernate.common.CommonUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@EnableScheduling
@SpringBootApplication(scanBasePackages = {
        "ch6_hibernate.p360_EntityGraph", "ch6_hibernate.common" })
public class EntityGraphMain {

    public static void main(String[] args) {
        CommonUtils.setProperties("p360_EntityGraph");
        SpringApplication.run(EntityGraphMain.class, args);
    }

}
