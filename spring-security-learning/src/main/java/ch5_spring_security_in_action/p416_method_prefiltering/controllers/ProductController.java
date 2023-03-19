package ch5_spring_security_in_action.p416_method_prefiltering.controllers;

import ch5_spring_security_in_action.p416_method_prefiltering.model.Product;
import ch5_spring_security_in_action.p416_method_prefiltering.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/sell")
    public List<Product> sellProduct() {
        List<Product> products = new ArrayList<>();

        products.add(new Product("beer", "nikolai"));
        products.add(new Product("candy", "nikolai"));
        products.add(new Product("chocolate", "pavel"));
        // если использовать immutable List.of() - получим 500 Internal Server Error
        // потому что List<Product> products изеняется аспектом.

        // send products from all users
        return productService.sellProducts(products);
    }

    @GetMapping("/find")
    public List<Product> findProducts() {
        return productService.findProducts();
    }
}
