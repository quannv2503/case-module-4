package com.example.test.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class StatisticsByQuantityOfCategory {
    @Id
    private Long id;
    private String name;
    private Integer sumquantity;
    private Double sumbycategory;

    public StatisticsByQuantityOfCategory() {
    }

    public StatisticsByQuantityOfCategory(Long id, String name, Integer sumquantity,Double sumbycategory) {
        this.id = id;
        this.name = name;
        this.sumquantity = sumquantity;
        this.sumbycategory = sumbycategory;
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

    public Double getSumbycategory() {
        return sumbycategory;
    }

    public void setSumbycategory(Double sumbycategory) {
        this.sumbycategory = sumbycategory;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSumquantity() {
        return sumquantity;
    }

    public void setSumquantity(Integer sumquantity) {
        this.sumquantity = sumquantity;
    }
}
