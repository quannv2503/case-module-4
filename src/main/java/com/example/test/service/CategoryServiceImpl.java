package com.example.test.service;


import com.example.test.model.Category;
import com.example.test.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;


public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public Iterable<Category> list() {
        return categoryRepository.findAll();
    }

    @Override
    public Category findCategoryById(Long id) {
        return categoryRepository.findById(id).get();
    }
}
