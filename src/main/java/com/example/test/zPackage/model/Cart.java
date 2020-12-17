package com.example.test.zPackage.model;

import com.example.test.model.Product;
import lombok.Data;


import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Data
public class Cart {

    List<ItemLine> itemLines = new ArrayList<>();
    public ItemLine getItemLineByItem(Product product){
        for (ItemLine itemLine: itemLines){
            if (itemLine.getProduct().getId().equals(product.getId())) return itemLine;
        }
        ItemLine itemLine = new ItemLine(product);
        itemLines.add(itemLine);
        return itemLine;
    }
    public void removeItemLineByProduct(Product product){
        for (ItemLine itemLine: itemLines){
            if (itemLine.getProduct().getId().equals(product.getId())){
                itemLines.remove(itemLine);
                return;
            }
        }
    }
}
