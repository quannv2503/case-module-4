package com.example.test.zPackage.service.ProductService;

import com.example.test.model.Product;
import com.example.test.zPackage.repository.ProductRepositoryZ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class ProductServiceZImpl implements ProductServiceZ {

    @Autowired
    ProductRepositoryZ productRepositoryZ;

    @Override
    public Page<Product> findAll(Pageable pageable) {
        return productRepositoryZ.findAll(pageable);
    }
}
