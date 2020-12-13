package com.example.test.controller;

import com.example.test.model.*;
import com.example.test.service.CategoryService;
import com.example.test.service.OrderDetailService;
import com.example.test.service.ProductService;
import com.example.test.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Controller
@SessionAttributes("seller-session")
@RequestMapping(value = "/sell")
public class SellController {
    @Autowired
    ProductService productService;
    @Autowired
    SellerService sellerService;

    @GetMapping(value = "/home")
    public ModelAndView show(@RequestParam("search") Optional<String> search, Pageable pageable, @SessionAttribute(value = "seller-session") Seller se) {
        Seller seller = sellerService.findById(se.getId());
        Page<Product> products;
        ModelAndView modelAndView = new ModelAndView("sell/list");
        if (search.isPresent()) {
            products = productService.findAllByNameContaining(search.get(), se.getId(), "1", pageable);
            modelAndView.addObject("search", search.get());
        } else {
            products = productService.list(se.getId(), "1", pageable);
        }
//        modelAndView.addObject("seller_id",se.getId());
        modelAndView.addObject("p", new Product());
        modelAndView.addObject("products", products);
        return modelAndView;
    }

    @PostMapping(value = "/add-product")
    public String addProduct(@ModelAttribute("product") Product product, @RequestParam("add-file") String file, @SessionAttribute(value = "seller-session") Seller se) {
        product.setImage(file);
        product.setSeller(se);
        productService.save(product);
        return "redirect:/sell/home";
    }

    @GetMapping(value = "/divide/{id}")
    public ModelAndView divide(@PathVariable(value = "id") Long id, Pageable pageable, @SessionAttribute(value = "seller-session") Seller se) {
        ModelAndView modelAndView = new ModelAndView("sell/list");
        Page<Product> products = productService.listDivide(id, se.getId(), "1", pageable);
        modelAndView.addObject("p", new Product());
        modelAndView.addObject("products", products);
        modelAndView.addObject("divide_id", id);
        return modelAndView;
    }

    @GetMapping(value = "/login")
    public ModelAndView login(@ModelAttribute("message") String message,
                              HttpServletResponse response, HttpServletRequest request, @ModelAttribute("seller-session") Seller se) {
        ModelAndView modelAndView = new ModelAndView("sell/login");
//        Long i= Long.valueOf(1);
//        List<OrderConfirmation> list= (List<OrderConfirmation>) productService.listOrderConfirmation(i);
//        System.out.println("kkkkkkk"+list.get(0).getAddress());
        Cookie[] cookies = request.getCookies();
        //iterate each cookie
        for (Cookie ck : cookies) {
            //display only the cookie with the name 'setUser'
            if (ck.getName().equals("ck-id")) {
                if (ck.getValue().equals("-1")) {
                    modelAndView.addObject("username", "");
                    modelAndView.addObject("password", "");
                    break;
                } else {
                    Long id_seller = Long.valueOf(ck.getValue());
                    Seller seller = sellerService.findById(id_seller);
                    modelAndView.addObject("username", seller.getAccount());
                    modelAndView.addObject("password", seller.getPassword());
                    break;
                }
            } else {
                ck.setValue("");
                modelAndView.addObject("username", "");
                modelAndView.addObject("password", "");
                break;
            }
        }
        modelAndView.addObject("message", message);
        return modelAndView;
    }

    @GetMapping(value = "/logout")
    public ModelAndView logout(@ModelAttribute("seller-session") Seller se) {
        ModelAndView modelAndView = new ModelAndView("redirect:/sell/login");
        Long id = Long.valueOf(-1);
        se.setId(id);
        return modelAndView;
    }

    @GetMapping(value = "/logup")
    public ModelAndView logup() {
        ModelAndView modelAndView = new ModelAndView("sell/logup");
        return modelAndView;
    }

    @PostMapping(value = "/edit-product")
    public String editProduct(@RequestParam("id") Long id, @RequestParam("name") String name, @RequestParam("price") Double price, @RequestParam("quantity") Integer quantity, @RequestParam("discount") Integer discount, @RequestParam("category") Long category_id, @RequestParam("description") String description, @RequestParam("image") String image, @SessionAttribute(value = "seller-session") Seller se) {
        Category category = categoryService.findCategoryById(category_id);
        Product product;
        String img = productService.findProduct(id).getImage();
        System.out.println("img" + img);
        if (image.length() < 3) {
            product = new Product(id, name, quantity, discount, price, category, description, img, se);
        } else {
            product = new Product(id, name, quantity, discount, price, category, description, image, se);
        }
        productService.save(product);
        return "redirect:/sell/home";
    }

    @PostMapping("/checklogin")
    public ModelAndView checkLogIn(@RequestParam("username") String username, @RequestParam("password") String password,
                                   @RequestParam(value = "checkbox", defaultValue = "no") String checkbox,
                                   RedirectAttributes redirectAttributes, @ModelAttribute("seller-session") Seller se,
                                   @CookieValue(value = "ck-id", defaultValue = "") String ck_id,
                                   HttpServletResponse response, HttpServletRequest request) {
        ModelAndView modelAndView;
        Seller seller = sellerService.checkLogIn(username, password);
        if (seller == null) {
            redirectAttributes.addFlashAttribute("message", "Tài khoản hoặc mật khẩu không chính xác");
            modelAndView = new ModelAndView("redirect:/sell/login");
        } else {
            modelAndView = new ModelAndView("redirect:/sell/home");
            se.setId(seller.getId());
            if (checkbox.equals("ok")) {
                ck_id = String.valueOf(seller.getId());
                Cookie cookie = new Cookie("ck-id", ck_id);
                cookie.setMaxAge(24 * 60 * 60);
                response.addCookie(cookie);
            } else {
                String id_ck_new = "-1";
                Cookie cookie = new Cookie("ck-id", id_ck_new);
                cookie.setMaxAge(24 * 60 * 60);
                response.addCookie(cookie);
            }
        }
        return modelAndView;
    }

    @PostMapping(value = "/delete")
    public String delete(@RequestParam("id") Long id) {
        Product product = productService.findProduct(id);
        product.setActive("2");
        productService.save(product);
        return "redirect:/sell/home";
    }


    @Autowired
    CategoryService categoryService;

    @ModelAttribute(value = "categorys")
    public Iterable<Category> categorys() {
        return categoryService.list();
    }

    @ModelAttribute("seller-session")
    public Seller setSeller() {
        return new Seller();
    }

    @GetMapping(value = "/order-confirm")
    public ModelAndView orderConfirm() {
        ModelAndView modelAndView = new ModelAndView("sell/order-confirmation");
        Product product = new Product();
        List<Product> products = new ArrayList<>();
        products.add(product);
        products.add(product);
        products.add(product);
        products.add(product);
        products.add(product);
        products.add(product);
        modelAndView.addObject("books", products);
        return modelAndView;
    }

    @Autowired
    OrderDetailService orderDetailService;

    @GetMapping(value = "/confirm/{id}")
    public String confirm(@PathVariable("id") Long idOrderDetail) {
        OrderDetail orderDetail = orderDetailService.findOrderDetailById(idOrderDetail);
        orderDetail.setStatus("2");
        orderDetailService.save(orderDetail);
        return "redirect:/sell/order-confirm";
    }

    @GetMapping(value = "/refuse/{id}")
    public String refuse(@PathVariable("id") Long idOrderDetail) {
        OrderDetail orderDetail = orderDetailService.findOrderDetailById(idOrderDetail);
        orderDetail.setStatus("3");
        orderDetailService.save(orderDetail);
        return "redirect:/sell/order-confirm";
    }
}
