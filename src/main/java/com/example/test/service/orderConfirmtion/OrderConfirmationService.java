package com.example.test.service.orderConfirmtion;

import com.example.test.model.OrderConfirmation;
import com.example.test.repository.OrderDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;

public interface OrderConfirmationService {
    Iterable<OrderConfirmation> listOrderConfirmation(Long id);
}
