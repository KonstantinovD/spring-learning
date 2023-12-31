package ch6_hibernate.p259_complex_keys_in_db;

import ch6_hibernate.common.CommonUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@EnableScheduling
@SpringBootApplication(scanBasePackages = {"ch6_hibernate.p259_complex_keys_in_db", "ch6_hibernate.common"})
public class ComplexKeysInDBMain {

    public static void main(String[] args) {
        CommonUtils.setProperties("p259_complex_keys_in_db");
        SpringApplication.run(ComplexKeysInDBMain.class, args);
    }

}
