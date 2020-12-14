package com.example.test.zPackage.controller;


import com.example.test.model.Product;
import com.example.test.zPackage.service.CategoryService.CategoryService;
import com.example.test.zPackage.service.ProductService.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("")
public class ProductController {
    @Autowired
    ProductService productService;

    @Autowired
    CategoryService categoryService;

    @GetMapping("/")
    public ModelAndView showProduct(Pageable pageable) {
        ModelAndView modelAndView = new ModelAndView("views-home/index");
        Page<Product> products = productService.findAll(pageable);


//        modelAndView.addObject()
        return modelAndView;
    }



}
