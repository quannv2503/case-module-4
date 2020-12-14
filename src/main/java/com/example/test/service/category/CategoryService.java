package com.example.test.service.category;


import com.example.test.model.Category;

public interface CategoryService {
    Iterable<Category> list();
    Category findCategoryById(Long id);
}
