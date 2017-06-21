package model.product;

import domainapp.basics.model.meta.DClass;


@DClass(schema = "internetCafe")
public class Food extends Product {
    public Food(String productId, String productName, Integer productPrice, Integer productCost) {
        super(productId, productName, productPrice, productCost);
    }

    public Food(String productName, Integer productPrice, Integer productCost) {
        super(productName, productPrice, productCost);
    }
}
