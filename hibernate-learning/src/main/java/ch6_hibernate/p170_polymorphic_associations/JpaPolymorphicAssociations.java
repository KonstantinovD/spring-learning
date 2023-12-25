package ch6_hibernate.p170_polymorphic_associations;

import ch6_hibernate.common.CommonUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@EnableScheduling
@SpringBootApplication
public class JpaPolymorphicAssociations {

    public static void main(String[] args) {
        CommonUtils.setProperties("p170_polymorphic_associations");
        SpringApplication.run(JpaPolymorphicAssociations.class, args);
    }

}
