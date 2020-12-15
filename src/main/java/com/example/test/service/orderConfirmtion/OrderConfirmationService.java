package com.example.test.service.orderConfirmtion;

import com.example.test.model.OrderConfirmation;
import com.example.test.repository.OrderDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;

public interface OrderConfirmationService {
    Iterable<OrderConfirmation> listOrderWait(Long id);

    void save(Iterable<OrderConfirmation> orderConfirmations);
    Iterable<OrderConfirmation> listOrderConfirmed(Long id);
    Iterable<OrderConfirmation> listOrderRefuse(Long id);

}
