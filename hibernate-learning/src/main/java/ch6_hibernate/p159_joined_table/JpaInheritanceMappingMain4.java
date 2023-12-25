package ch6_hibernate.p159_joined_table;

import ch6_hibernate.common.CommonUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@EnableScheduling
@SpringBootApplication
public class JpaInheritanceMappingMain4 {

    public static void main(String[] args) {
        CommonUtils.setProperties("p159_joined_table");
        SpringApplication.run(JpaInheritanceMappingMain4.class, args);
    }

}
