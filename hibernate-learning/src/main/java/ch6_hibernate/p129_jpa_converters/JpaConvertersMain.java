package ch6_hibernate.p129_jpa_converters;

import ch6_hibernate.common.CommonUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@EnableScheduling
@SpringBootApplication
public class JpaConvertersMain {

    public static void main(String[] args) {
        CommonUtils.setProperties("p129_jpa_converters");
        SpringApplication.run(JpaConvertersMain.class, args);
    }

}
