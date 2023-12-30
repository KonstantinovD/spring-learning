package ch6_hibernate.p220_oneToOne_joined_table;

import ch6_hibernate.common.CommonUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@EnableScheduling
@SpringBootApplication
public class OneToOneJoinedTableMain {

    public static void main(String[] args) {
        CommonUtils.setProperties("p220_oneToOne_joined_table");
        SpringApplication.run(OneToOneJoinedTableMain.class, args);
    }

}
