package com.example.test.service.category;


import com.example.test.model.Category;
import com.example.test.repository.CategoryRepository;
import com.example.test.service.category.CategoryService;
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
