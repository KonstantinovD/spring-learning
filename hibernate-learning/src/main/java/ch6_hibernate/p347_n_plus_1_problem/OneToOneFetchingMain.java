package ch6_hibernate.p347_n_plus_1_problem;

import ch6_hibernate.common.CommonUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@EnableScheduling
@SpringBootApplication(scanBasePackages = { "ch6_hibernate.p347_n_plus_1_problem", "ch6_hibernate.common" })
public class OneToOneFetchingMain {

    public static void main(String[] args) {
        CommonUtils.setProperties("p347_n_plus_1_problem");
        SpringApplication.run(OneToOneFetchingMain.class, args);
    }

}
