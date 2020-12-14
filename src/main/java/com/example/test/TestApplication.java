package com.example.test;

import com.example.test.service.*;
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

    @Bean
    public SellerService sellerService() {
        return new SellerServiceImpl();
    }

    @Bean
    public OrderDetailService orderDetailService() {
        return new OrderDetailImpl();
    }

    @Bean
    public OrderConfirmationService orderConfirmationService() {
        return new OrderConfirmationServiceImpl();
    }

}
