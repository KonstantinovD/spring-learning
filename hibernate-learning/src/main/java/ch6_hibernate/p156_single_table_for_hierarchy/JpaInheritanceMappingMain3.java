package ch6_hibernate.p156_single_table_for_hierarchy;

import ch6_hibernate.common.CommonUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@EnableScheduling
@SpringBootApplication
public class JpaInheritanceMappingMain3 {

    public static void main(String[] args) {
        CommonUtils.setProperties("p156_single_table_for_hierarchy");
        SpringApplication.run(JpaInheritanceMappingMain3.class, args);
    }

}
