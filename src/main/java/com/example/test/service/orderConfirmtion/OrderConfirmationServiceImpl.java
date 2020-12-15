package com.example.test.service.orderConfirmtion;

import com.example.test.model.OrderConfirmation;
import com.example.test.repository.OrderConfirmationRepository;
import com.example.test.service.orderConfirmtion.OrderConfirmationService;
import org.springframework.beans.factory.annotation.Autowired;

public class OrderConfirmationServiceImpl implements OrderConfirmationService {
    @Autowired
    OrderConfirmationRepository orderConfirmationRepository;

    @Override
    public Iterable<OrderConfirmation> listOrderWait(Long id) {
        return orderConfirmationRepository.listOrderWait(id);
    }

    @Override
    public void save(Iterable<OrderConfirmation> orderConfirmations) {
        orderConfirmationRepository.saveAll(orderConfirmations);
    }
    @Override
    public Iterable<OrderConfirmation> listOrderConfirmed(Long id) {
        return orderConfirmationRepository.listOrderConfirmed(id);
    }
    @Override
    public Iterable<OrderConfirmation> listOrderRefuse(Long id) {
        return orderConfirmationRepository.listOrderRefuse(id);
    }


}
