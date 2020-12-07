package com.example.model;

import javax.persistence.*;

@Entity
public class OrderDetial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int quantityBuy;
    private double price;
    private String status; // trong DB mặc định là trạng thái chờ
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public OrderDetial() {
    }

    public OrderDetial(int quantityBuy, double price, Product product) {
        this.quantityBuy = quantityBuy;
        this.price = price;
        this.product = product;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getQuantityBuy() {
        return quantityBuy;
    }

    public void setQuantityBuy(int quantityBuy) {
        this.quantityBuy = quantityBuy;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
