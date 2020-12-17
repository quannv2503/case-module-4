package com.example.test.zPackage.controller;


import com.example.test.model.Category;
import com.example.test.model.Product;
import com.example.test.model.Seller;
import com.example.test.zPackage.service.CategoryService.CategoryServiceZ;
import com.example.test.zPackage.service.ProductService.ProductServiceZ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
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
    public ModelAndView showProduct() {
        ModelAndView modelAndView = new ModelAndView("views-home/index");
        modelAndView.addObject("products",productServiceZ.findAll());
        modelAndView.addObject("categories", categories());
        return modelAndView;
    }

    @Autowired
    Environment env;

    @GetMapping("/create")
    public ModelAndView showCreateProduct() {
        ModelAndView modelAndView = new ModelAndView("/product/create");
        modelAndView.addObject("product", new Product());
        return modelAndView;
    }

    @GetMapping
    public ModelAndView listProducts(){
        Iterable<Product> products = productServiceZ.findAll();
        ModelAndView modelAndView = new ModelAndView("product/list");
        modelAndView.addObject("products", products);
        return modelAndView;
    }
    @GetMapping("/{id}")
    public String viewProduct(@PathVariable Long id, Model model){
        Product product = productServiceZ.findById(id);
        model.addAttribute("product",product);
        return "product/view";
    }




}
