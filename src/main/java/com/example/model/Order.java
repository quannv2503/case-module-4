package com.example.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private java.sql.Timestamp date; // trong DB để mặc định là CURRENT_TIMESTAMP
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    @OneToMany
    @JoinColumn(name = "orderdetail_id")
    private List<OrderDetial> orderDetialList;

    public Order() {
    }

}
