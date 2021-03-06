package com.example.test.service.seller;

import com.example.test.model.Seller;
import com.example.test.repository.SellerRepository;
import com.example.test.service.seller.SellerService;
import org.springframework.beans.factory.annotation.Autowired;

public class SellerServiceImpl implements SellerService {
    @Autowired
    SellerRepository sellerRepository;

    @Override
    public Seller checkLogIn(String username, String password) {
        return sellerRepository.checkLogIn(username, password);
    }

    @Override
    public Seller findById(Long id) {
        return sellerRepository.findById(id).get();
    }

    @Override
    public void save(Seller seller) {
        sellerRepository.save(seller);
    }

    @Override
    public Iterable<Seller> findAll() {
        return sellerRepository.findAll();
    }
}
