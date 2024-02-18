package ch6_hibernate.p351_batch_size_np1_solution;

import ch6_hibernate.common.CommonUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@EnableScheduling
@SpringBootApplication(scanBasePackages = {
        "ch6_hibernate.p351_batch_size_np1_solution", "ch6_hibernate.common" })
public class BatchSizeAnnotationMain {

    public static void main(String[] args) {
        CommonUtils.setProperties("p351_batch_size_np1_solution");
        SpringApplication.run(BatchSizeAnnotationMain.class, args);
    }

}
