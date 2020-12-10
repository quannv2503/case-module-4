package com.example.test;

import com.example.test.service.CategoryService;
import com.example.test.service.CategoryServiceImpl;
import com.example.test.service.ProductService;
import com.example.test.service.ProductServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TestApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestApplication.class, args);
    }

    @Bean
    public CategoryService serviceCategory() {
        return new CategoryServiceImpl();
    }

    @Bean
    public ProductService serviceProduct() {
        return new ProductServiceImpl();
    }
}
