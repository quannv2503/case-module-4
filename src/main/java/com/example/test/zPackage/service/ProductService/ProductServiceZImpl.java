package com.example.test.zPackage.service.ProductService;

import com.example.test.model.Category;
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

    @Override
    public Product findById(Long id) {
        return productRepositoryZ.findById(id).get();
    }

    @Override
    public void save(Product product) {
        productRepositoryZ.save(product);
    }

    @Override
    public void remove(Product product) {
        productRepositoryZ.delete(product);
    }

    @Override
    public Iterable<Product> findAllByCategory(Category category) {
        return productRepositoryZ.findAllByCategory(category);
    }

    @Override
    public Page<Product> findAllByNameContaining(String name, Pageable pageable) {
        return productRepositoryZ.findAllByNameContaining(name, pageable);
    }
}
