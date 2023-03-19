package ch5_spring_security_in_action.p416_method_prefiltering.service;

import ch5_spring_security_in_action.p416_method_prefiltering.model.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class ProductService {

    @PreFilter("filterObject.owner == authentication.name")
    public List<Product> sellProducts(List<Product> products) {
        log.info("received and filtered products: {}", products);
        // sell products and return the sold products list
        return products;
    }

    @PostFilter("filterObject.owner == authentication.name")
    public List<Product> findProducts() {
        // должен быть immutable list
        List<Product> products = new ArrayList<>();
        products.add(new Product("beer", "nikolai"));
        products.add(new Product("candy", "nikolai"));
        products.add(new Product("chocolate", "pavel"));
        return products;
    }
}
