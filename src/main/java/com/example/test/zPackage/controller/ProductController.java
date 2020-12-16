package com.example.test.zPackage.controller;


import com.example.test.model.Category;
import com.example.test.model.Product;
import com.example.test.model.Seller;
import com.example.test.zPackage.service.CategoryService.CategoryServiceZ;
import com.example.test.zPackage.service.ProductService.ProductServiceZ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("")
public class ProductController {
    @Autowired
    ProductServiceZ productServiceZ;

    @Autowired
    CategoryServiceZ categoryServiceZ;

    @ModelAttribute
    public Iterable<Category> categories() {
        return categoryServiceZ.findAll();
    }

    @GetMapping("/")
    public ModelAndView showProduct(@RequestParam("search") Optional<String> s, Pageable pageable) {

        Page<Product> products;
        ModelAndView modelAndView = new ModelAndView("views-home/index");
        if (s.isPresent()) {
            products = productServiceZ.findAllByNameContaining(s.get(), pageable);
            modelAndView.addObject("search", s.get());
        } else {
            products = productServiceZ.findAll(pageable);
        }
        modelAndView.addObject("categories", categories());
        modelAndView.addObject("products", products);
        return modelAndView;
    }




}
