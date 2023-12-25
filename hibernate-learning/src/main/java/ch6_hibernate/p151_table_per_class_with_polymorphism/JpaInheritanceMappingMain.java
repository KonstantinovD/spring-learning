package ch6_hibernate.p151_table_per_class_with_polymorphism;

import ch6_hibernate.common.CommonUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@EnableScheduling
@SpringBootApplication
public class JpaInheritanceMappingMain {

    public static void main(String[] args) {
        CommonUtils.setProperties("p151_table_per_class_with_polymorphism");
        SpringApplication.run(JpaInheritanceMappingMain.class, args);
    }

}
