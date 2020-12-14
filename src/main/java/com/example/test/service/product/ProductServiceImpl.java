package com.example.test.service.product;


import com.example.test.model.Product;
import com.example.test.repository.ProductRepository;
import com.example.test.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;

    @Override
    public Page<Product> list(Long id, String active, Pageable pageable) {
        return productRepository.findProductsBySellerIdAndActiveIs(id, active, pageable);
    }

    @Override
    public void save(Product product) {
        productRepository.save(product);
    }

    @Override
    public void delete(Product product) {
        productRepository.delete(product);
    }

    @Override
    public Product findProduct(Long id) {
        return productRepository.findById(id).get();
    }

    @Override
    public Page<Product> listDivide(Long id1, Long id2, String active, Pageable pageable) {
        return productRepository.findProductsByCategoryIdAndSeller_IdAndActiveIs(id1, id2, active, pageable);
    }

    @Override
    public Page<Product> findAllByNameContaining(String name, Long id, String active, Pageable pageable) {
        return productRepository.findProductsByNameContainingAndSeller_IdAndActiveIs(name, id, active, pageable);
    }

}
