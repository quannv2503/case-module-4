package com.example.test.service.seller;

import com.example.test.model.Seller;

public interface SellerService {
    Seller checkLogIn(String username, String password);

    Seller findById(Long id);

    void save(Seller seller);
 Iterable<Seller> findAll();
}
