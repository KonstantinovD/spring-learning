package ch6_hibernate.p999_presentation_sn1;

import ch6_hibernate.common.CommonUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@EnableScheduling
@SpringBootApplication(scanBasePackages = {
        "ch6_hibernate.p999_presentation_sn1", "ch6_hibernate.common" })
public class Presentation1Main {

    public static void main(String[] args) {
        CommonUtils.setProperties("p999_presentation_sn1");
        SpringApplication.run(Presentation1Main.class, args);
    }

}
