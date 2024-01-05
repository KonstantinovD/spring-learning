package ch6_hibernate.p279_1_insert_id_order;

import ch6_hibernate.common.CommonUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@EnableScheduling
@SpringBootApplication(scanBasePackages = { "ch6_hibernate.p279_1_insert_id_order", "ch6_hibernate.common" })
public class InsertIdOrderMain {

    public static void main(String[] args) {
        CommonUtils.setProperties("p279_1_insert_id_order");
        SpringApplication.run(InsertIdOrderMain.class, args);
    }

}
