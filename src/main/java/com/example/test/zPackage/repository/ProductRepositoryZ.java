package com.example.test.zPackage.repository;

import com.example.test.model.Category;
import com.example.test.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;



public interface ProductRepositoryZ extends CrudRepository<Product, Long> {

    Iterable<Product> findAllByCategory(Category category);

    Iterable<Product> findAllByNameContaining(String name);

}
