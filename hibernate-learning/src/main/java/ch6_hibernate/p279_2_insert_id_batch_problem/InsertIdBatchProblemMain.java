package ch6_hibernate.p279_2_insert_id_batch_problem;

import ch6_hibernate.common.CommonUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@EnableScheduling
@SpringBootApplication(scanBasePackages = { "ch6_hibernate.p279_2_insert_id_batch_problem", "ch6_hibernate.common" })
public class InsertIdBatchProblemMain {

    public static void main(String[] args) {
        CommonUtils.setProperties("p279_2_insert_id_batch_problem");
        SpringApplication.run(InsertIdBatchProblemMain.class, args);
    }

}
