package ch6_hibernate.p188_embeddable_components_collections;

import ch6_hibernate.common.CommonUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@EnableScheduling
@SpringBootApplication
public class EmbeddableComponentsCollectionsMain {

    public static void main(String[] args) {
        CommonUtils.setProperties("p188_embeddable_components_collections");
        SpringApplication.run(EmbeddableComponentsCollectionsMain.class, args);
    }

}
