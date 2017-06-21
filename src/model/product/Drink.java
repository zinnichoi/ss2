package model.product;

import domainapp.basics.model.meta.DClass;


@DClass(schema = "internetCafe")
public class Drink extends Product {
    public Drink(String productName, Integer productPrice, Integer productCost) {
        super(productName, productPrice, productCost);
    }

    public Drink(String productId, String productName, Integer productPrice, Integer
            productCost) {
        super(productId, productName, productPrice, productCost);
    }
}
