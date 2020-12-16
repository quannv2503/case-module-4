package com.example.test.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class StatisticsByProduct {
    @Id
    private Long id;
    private String name;
    private Double price;
    private Integer discount;
    private String description;
    private Integer quantity_selled;
    private Double sum;

    public StatisticsByProduct() {
    }

    public StatisticsByProduct(Long id, String name, Double price, Integer discount, String description, Integer quantity_selled, Double sum) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.discount = discount;
        this.quantity_selled = quantity_selled;
        this.description = description;
        this.sum = sum;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getQuantity_selled() {
        return quantity_selled;
    }

    public void setQuantity_selled(Integer quantity_selled) {
        this.quantity_selled = quantity_selled;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getSum() {
        Double s = Double.valueOf((Math.round(sum * 1000) / 1000));
        return s;
    }

    public void setSum(Double sum) {
        this.sum = sum;
    }
}
