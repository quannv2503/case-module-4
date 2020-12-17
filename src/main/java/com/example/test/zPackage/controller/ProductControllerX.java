package com.example.test.zPackage.controller;

import com.example.test.model.Product;
import com.example.test.zPackage.service.ProductService.ProductServiceZ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/products")
public class ProductControllerX {

    @Autowired
    ProductServiceZ productServiceZ;

    @Autowired
    Environment env;

    @GetMapping
    public ModelAndView listProducts() {
        Iterable<Product> products = productServiceZ.findAll();
        ModelAndView modelAndView = new ModelAndView("/views-home/product/list");
        modelAndView.addObject("products", products);
        return modelAndView;
    }

    @GetMapping("/{id}")
    public String viewProduct(@PathVariable Long id, Model model) {
        Product product = productServiceZ.findById(id);
        model.addAttribute("product", product);
        return "/views-home/product/view";
    }
}