package ch6_hibernate.p123_embedded_embeddable_classes;

import ch6_hibernate.common.CommonUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class EmbeddableClassesMain {
    public static void main(String[] args) {
        CommonUtils.setProperties("p123_embedded_embeddable_classes");
        SpringApplication.run(EmbeddableClassesMain.class, args);
    }

}