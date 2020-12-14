package com.example.test.zPackage.service.CategoryService;

import com.example.test.model.Category;
import com.example.test.zPackage.repository.CategoryRepositoryZ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceZImpl implements CategoryServiceZ {
    @Autowired
    CategoryRepositoryZ categoryRepositoryZ;


    @Override
    public Iterable<Category> findAll() {
        return categoryRepositoryZ.findAll();
    }
}
