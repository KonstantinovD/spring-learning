package ch6_hibernate.p288_SessionFlushing;

import ch6_hibernate.common.CommonUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@EnableScheduling
@SpringBootApplication(scanBasePackages = { "ch6_hibernate.p288_SessionFlushing", "ch6_hibernate.common" })
public class SessionFlushingMain {

    public static void main(String[] args) {
        CommonUtils.setProperties("p288_SessionFlushing");
        // System.setProperty("org.hibernate.flushMode", "COMMIT"); // AUTO

        SpringApplication.run(SessionFlushingMain.class, args);
    }

}
