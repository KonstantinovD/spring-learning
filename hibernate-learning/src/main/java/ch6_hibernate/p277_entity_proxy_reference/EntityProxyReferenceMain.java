package ch6_hibernate.p277_entity_proxy_reference;

import ch6_hibernate.common.CommonUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@EnableScheduling
@SpringBootApplication(scanBasePackages = { "ch6_hibernate.p277_entity_proxy_reference", "ch6_hibernate.common" })
public class EntityProxyReferenceMain {

    public static void main(String[] args) {
        CommonUtils.setProperties("p277_entity_proxy_reference");
        SpringApplication.run(EntityProxyReferenceMain.class, args);
    }

}
