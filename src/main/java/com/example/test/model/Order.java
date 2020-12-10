//package com.example.test.model;
//
//import javax.persistence.*;
//import java.util.List;
//
//@Entity
//public class Order {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//    private java.sql.Timestamp date; // trong DB để mặc định là CURRENT_TIMESTAMP
//    @ManyToOne
//    @JoinColumn(name = "customer_id")
//    private Customer customer;
//    @OneToMany
//    @JoinColumn(name = "orderdetail_id")
//    private List<OrderDetail> orderDetailList;
//
//    public Order() {
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public Customer getCustomer() {
//        return customer;
//    }
//
//    public void setCustomer(Customer customer) {
//        this.customer = customer;
//    }
//
//    public List<OrderDetail> getOrderDetailList() {
//        return orderDetailList;
//    }
//
//    public void setOrderDetailList(List<OrderDetail> orderDetailList) {
//        this.orderDetailList = orderDetailList;
//    }
//}