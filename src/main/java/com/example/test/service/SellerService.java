package com.example.test.service;

import com.example.test.model.Seller;

public interface SellerService {
Seller checkLogIn(String username,String password);
Seller findById(Long id);
}
