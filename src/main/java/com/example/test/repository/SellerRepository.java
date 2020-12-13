package com.example.test.repository;

import com.example.test.model.Seller;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface SellerRepository extends CrudRepository<Seller, Long> {
    @Query("select s from Seller s where s.account =?1 and s.password=?2")
    Seller checkLogIn(String username, String password);
}
