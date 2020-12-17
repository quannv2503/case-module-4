package com.example.test.zPackage.model;

import com.example.test.model.Product;
import lombok.Data;

import javax.persistence.*;

@Data
public class ItemLine {
    private Long id;

    private Product product;
    private int quantity;
    private double price;
    public ItemLine(Product product){
        this.product = product;
        this.price = product.getPrice();
    }

}
