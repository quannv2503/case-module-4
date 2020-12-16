package com.example.test.zPackage.repository;

import com.example.test.model.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CategoryRepositoryZ extends PagingAndSortingRepository<Category, Long> {

}
