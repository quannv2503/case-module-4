package com.example.test.service;

import com.example.test.model.OrderConfirmation;
import com.example.test.repository.OrderConfirmationRepository;
import com.example.test.repository.OrderDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class OrderConfirmationServiceImpl implements OrderConfirmationService {
    @Autowired
    OrderConfirmationRepository orderConfirmationRepository;

    @Override
    public Iterable<OrderConfirmation> listOrderConfirmation(Long id) {
        return orderConfirmationRepository.listOrderConfirmation(id);
    }
}
