package ch6_hibernate.p154_table_per_class_with_union;

import ch6_hibernate.common.CommonUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@EnableScheduling
@SpringBootApplication
public class JpaInheritanceMappingMain2 {

    public static void main(String[] args) {
        CommonUtils.setProperties("p154_table_per_class_with_union");
        SpringApplication.run(JpaInheritanceMappingMain2.class, args);
    }

}
