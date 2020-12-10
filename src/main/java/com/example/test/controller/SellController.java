package com.example.test.controller;

import com.example.test.model.Category;
import com.example.test.model.Product;
import com.example.test.service.CategoryService;
import com.example.test.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;


@Controller
@RequestMapping(value = "/sell")
public class SellController {
    @Autowired
    ProductService productService;

    @GetMapping(value = "/")
    public ModelAndView show(@RequestParam("search") Optional<String> search, Pageable pageable) {
        Page<Product> products;
        ModelAndView modelAndView = new ModelAndView("sell/list");
        if (search.isPresent()) {
            products = productService.findAllByNameContaining(search.get(), pageable);
            modelAndView.addObject("search", search.get());
        } else {
            products = productService.list(pageable);
        }
        modelAndView.addObject("p", new Product());
        modelAndView.addObject("products", products);
        return modelAndView;
    }

    @PostMapping(value = "/add-product")
    public String addProduct(@ModelAttribute("product") Product product, @RequestParam("add-file") String file) {
        product.setImage(file);
        productService.save(product);
        return "redirect:/sell/";
    }

    @GetMapping(value = "/divide/{id}")
    public ModelAndView divide(@PathVariable(value = "id") Long id, Pageable pageable) {
        ModelAndView modelAndView = new ModelAndView("sell/list");
        Page<Product> products = productService.listDivide(id, pageable);
        modelAndView.addObject("p", new Product());
        modelAndView.addObject("products", products);
        modelAndView.addObject("divide_id", id);
        return modelAndView;
    }

    @Autowired
    CategoryService categoryService;

    @ModelAttribute(value = "categorys")
    public Iterable<Category> categorys() {
        return categoryService.list();
    }
}
