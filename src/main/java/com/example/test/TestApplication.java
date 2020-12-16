package com.example.test;

import com.example.test.service.category.CategoryService;
import com.example.test.service.category.CategoryServiceImpl;
import com.example.test.service.orderConfirmtion.OrderConfirmationService;
import com.example.test.service.orderConfirmtion.OrderConfirmationServiceImpl;
import com.example.test.service.orderDetail.OrderDetailServiceImpl;
import com.example.test.service.orderDetail.OrderDetailService;
import com.example.test.service.product.ProductService;
import com.example.test.service.product.ProductServiceImpl;
import com.example.test.service.seller.SellerService;
import com.example.test.service.seller.SellerServiceImpl;
import com.example.test.service.statistics.StatisticsByProductService;
import com.example.test.service.statistics.StatisticsByProductServiceImpl;
import com.example.test.service.statistics.StatisticsByQuantityOfCategoryService;
import com.example.test.service.statistics.StatisticsByQuantityOfCategoryServiceImpl;
import com.example.test.zPackage.service.CategoryService.CategoryServiceZ;
import com.example.test.zPackage.service.CategoryService.CategoryServiceZImpl;
import com.example.test.zPackage.service.ProductService.ProductServiceZ;
import com.example.test.zPackage.service.ProductService.ProductServiceZImpl;
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
        return new OrderDetailServiceImpl();
    }

    @Bean
    public OrderConfirmationService orderConfirmationService() {
        return new OrderConfirmationServiceImpl();
    }

    @Bean
    public ProductServiceZ productServiceZ() {
        return new ProductServiceZImpl();
    }

    @Bean
    public CategoryServiceZ categoryServiceZ() {
        return new CategoryServiceZImpl();
    }

    @Bean
    public StatisticsByProductService statisticsByProductService() {
        return new StatisticsByProductServiceImpl();
    }

    @Bean
    public StatisticsByQuantityOfCategoryService statisticsByQuantityOfCategoryService() {
        return new StatisticsByQuantityOfCategoryServiceImpl();
    }
}
