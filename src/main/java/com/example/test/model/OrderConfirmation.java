package com.example.test.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class OrderConfirmation {
    @Id
    private Long id;
    private Long id_sp;
    private String name_sp;
    private String name_customer;
    private Integer quantityBy;
    private String phoneNumber;
    private String address;

    public OrderConfirmation() {
    }

    public OrderConfirmation(Long id_sp, String name_sp, String name_customer, Integer quantityBy, String phoneNumber, String address) {
        this.id_sp = id_sp;
        this.name_sp = name_sp;
        this.name_customer = name_customer;
        this.quantityBy = quantityBy;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId_sp() {
        return id_sp;
    }

    public void setId_sp(Long id_sp) {
        this.id_sp = id_sp;
    }

    public String getName_sp() {
        return name_sp;
    }

    public void setName_sp(String name_sp) {
        this.name_sp = name_sp;
    }

    public String getName_customer() {
        return name_customer;
    }

    public void setName_customer(String name_customer) {
        this.name_customer = name_customer;
    }

    public Integer getQuantityBy() {
        return quantityBy;
    }

    public void setQuantityBy(Integer quantityBy) {
        this.quantityBy = quantityBy;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
