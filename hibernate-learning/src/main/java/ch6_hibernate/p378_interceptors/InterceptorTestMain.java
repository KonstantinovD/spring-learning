package ch6_hibernate.p378_interceptors;

import ch6_hibernate.common.CommonUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@EnableScheduling
@SpringBootApplication(scanBasePackages = {
        "ch6_hibernate.p378_interceptors", "ch6_hibernate.common" })
public class InterceptorTestMain {

    public static void main(String[] args) {
        CommonUtils.setProperties("p378_interceptors");
        SpringApplication.run(InterceptorTestMain.class, args);
    }

}
