package ch6_hibernate.p361_SqlResultSetMapping;

import ch6_hibernate.common.CommonUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@EnableScheduling
@SpringBootApplication(scanBasePackages = {
        "ch6_hibernate.p361_SqlResultSetMapping", "ch6_hibernate.common" })
public class SqlResultSetMappingMain {

    public static void main(String[] args) {
        CommonUtils.setProperties("p361_SqlResultSetMapping");
        SpringApplication.run(SqlResultSetMappingMain.class, args);
    }

}
