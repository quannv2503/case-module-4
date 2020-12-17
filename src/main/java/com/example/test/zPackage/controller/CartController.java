package com.example.test.zPackage.controller;

import com.example.test.model.Product;
import com.example.test.zPackage.model.Cart;
import com.example.test.zPackage.model.ItemLine;
import com.example.test.zPackage.service.ProductService.ProductServiceZ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/cart")
@Controller
@SessionAttributes("cart")
public class CartController {
    @Autowired
    ProductServiceZ productServiceZ;

    @ModelAttribute("cart")
    public Cart getCart(){
        return new Cart();
    }

    @GetMapping
    public String showCart(@ModelAttribute("cart") Cart cart){
        return "/views-home/shoppingCart/cart";
    }

    @GetMapping("/{id}")
    public String orderProduct(@PathVariable Long id, @ModelAttribute("cart") Cart cart){
        Product product = productServiceZ.findById(id);
        ItemLine itemLine = cart.getItemLineByItem(product);
        itemLine.setQuantity(itemLine.getQuantity()+1);
        return "redirect:/cart";
    }
    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id, @ModelAttribute("cart") Cart cart){
        Product product = productServiceZ.findById(id);
        cart.removeItemLineByProduct(product);
        return "redirect:/cart";
    }

}
