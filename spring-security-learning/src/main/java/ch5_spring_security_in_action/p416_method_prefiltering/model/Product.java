package ch5_spring_security_in_action.p416_method_prefiltering.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode
public class Product {

    private String name;
    private String owner;

}
