package com.example.test.service.orderDetail;

import com.example.test.model.OrderDetail;
import com.example.test.repository.OrderDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class OrderDetailServiceImpl implements OrderDetailService {
    @Autowired
    OrderDetailRepository orderDetailRepository;

    @Override
    public Iterable<OrderDetail> list() {
        return orderDetailRepository.findAll();
    }

    @Override
    public OrderDetail findOrderDetailById(Long id) {
        return orderDetailRepository.findById(id).get();
    }

    @Override
    public void save(OrderDetail orderDetail) {
        orderDetailRepository.save(orderDetail);
    }

}
