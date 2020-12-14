package com.example.test.service;

import com.example.test.model.Category;
import com.example.test.model.OrderDetail;

public interface OrderDetailService {
    Iterable<OrderDetail> list();

    OrderDetail findOrderDetailById(Long id);

    void save(OrderDetail orderDetail);
}
