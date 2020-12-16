package com.example.test.controller;

import com.example.test.model.*;
import com.example.test.service.category.CategoryService;
import com.example.test.service.orderConfirmtion.OrderConfirmationService;
import com.example.test.service.orderDetail.OrderDetailService;
import com.example.test.service.product.ProductService;
import com.example.test.service.seller.SellerService;
import com.example.test.service.statistics.StatisticsByProductService;
import com.example.test.service.statistics.StatisticsByQuantityOfCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    @Autowired
    OrderConfirmationService orderConfirmationService;

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
        modelAndView.addObject("p", new Product());
        modelAndView.addObject("products", products);
        modelAndView.addObject("seller", sellerService.findById(se.getId()));

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
        modelAndView.addObject("seller", sellerService.findById(se.getId()));
        return modelAndView;
    }

    @GetMapping(value = "/login")
    public ModelAndView login(@ModelAttribute("message") String message, @ModelAttribute("account") String account,
                              @ModelAttribute("password") String password,
                              HttpServletResponse response, HttpServletRequest request, @ModelAttribute("seller-session") Seller se) {
        ModelAndView modelAndView = new ModelAndView("sell/login");
        if (!account.equals("") && !password.equals("")) {
            modelAndView.addObject("username", account);
            modelAndView.addObject("password", password);
            modelAndView.addObject("ok","Đăng kí thành công");
            return modelAndView;
        } else {

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
    }

    @GetMapping(value = "/logout")
    public ModelAndView logout(@ModelAttribute("seller-session") Seller se) {
        ModelAndView modelAndView = new ModelAndView("redirect:/sell/login");
        Long id = Long.valueOf(-1);
        se.setId(id);
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
    public ModelAndView orderConfirm(@ModelAttribute("seller-session") Seller se) {
        ModelAndView modelAndView = new ModelAndView("sell/order-confirmation");
        List<OrderConfirmation> listOrderWait = (List<OrderConfirmation>) orderConfirmationService.listOrderWait(se.getId());
        modelAndView.addObject("listOrderWait", listOrderWait);
        List<OrderConfirmation> listOrderConfirmed = (List<OrderConfirmation>) orderConfirmationService.listOrderConfirmed(se.getId());
        modelAndView.addObject("listOrderConfirmed", listOrderConfirmed);
        List<OrderConfirmation> listOrderRefuse = (List<OrderConfirmation>) orderConfirmationService.listOrderRefuse(se.getId());
        modelAndView.addObject("listOrderRefuse", listOrderRefuse);
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

    @GetMapping(value = "/confirm-all")
    public String confirmAll(@ModelAttribute("seller-session") Seller se) {
        List<OrderConfirmation> listOrderWait = (List<OrderConfirmation>) orderConfirmationService.listOrderWait(se.getId());
        for (int i = 0; i < listOrderWait.size(); i++) {
            OrderDetail orderDetail = orderDetailService.findOrderDetailById(listOrderWait.get(i).getId());
            orderDetail.setStatus("2");
            orderDetailService.save(orderDetail);
        }
        return "redirect:/sell/order-confirm";
    }

    @GetMapping(value = "/seller")
    public String seller(Model model, @ModelAttribute("seller-session") Seller se) {
        Seller seller = sellerService.findById(se.getId());
        model.addAttribute("seller", seller);
        return "sell/seller";
    }

    @PostMapping(value = "/update-seller")
    public String updateSeller(Model model, @ModelAttribute("seller") Seller sellerNew, @RequestParam("update_avt")
            String update_avt, @ModelAttribute("seller-session") Seller se) {
        Seller sellerOld = sellerService.findById(se.getId());
        if (sellerOld.getPassword().equals(sellerNew.getPassword())) {
            if (update_avt.length() < 3) {
                sellerNew.setAvatar(sellerOld.getAvatar());
                sellerService.save(sellerNew);
                model.addAttribute("seller", sellerNew);
            } else {
                sellerNew.setAvatar(update_avt);
                sellerService.save(sellerNew);
                model.addAttribute("seller", sellerNew);
            }
        } else {
            model.addAttribute("messenger", "Mật khẩu sai");
            model.addAttribute("seller", sellerOld);
        }

        return "sell/seller";
    }

    @PostMapping(value = "/change-password")
    public String changePassword(Model model, @ModelAttribute("seller-session") Seller se, @RequestParam("password") String
            password, @RequestParam("newPassword") String newPassword) {
        Seller sellerOld = sellerService.findById(se.getId());
        if ((!sellerOld.getPassword().equals(password)) || (password.equals(newPassword))) {
            model.addAttribute("messenger2", "Không thành công");
            model.addAttribute("seller", sellerOld);
        } else {
            sellerOld.setPassword(newPassword);
            sellerService.save(sellerOld);
            model.addAttribute("messenger3", "Đổi thành công");
            model.addAttribute("seller", sellerOld);
        }

        return "sell/seller";
    }

    @GetMapping(value = "/list-product")
    public ModelAndView listStoppedSelling(@ModelAttribute("seller-session") Seller se, @ModelAttribute("active") String active) {
        ModelAndView modelAndView = new ModelAndView("/sell/list-product");
        List<Product> allProduct = (List<Product>) productService.listProductsBySellerId(se.getId());
        modelAndView.addObject("allProduct", allProduct);
        List<Product> listStoppedSell = (List<Product>) productService.listActiveSelling(se.getId(), "2");
        modelAndView.addObject("listStoppedSell", listStoppedSell);
        List<Product> listSelling = (List<Product>) productService.listActiveSelling(se.getId(), "1");
        modelAndView.addObject("listSelling", listSelling);
        String act;
        if (active.equals("2") || active.equals("3")) {
            act = active;
        } else {
            act = "1";
        }
        modelAndView.addObject("active", act);
        return modelAndView;
    }

    @GetMapping(value = "/list-stopped/{id}")
    public ModelAndView listStopped(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        ModelAndView modelAndView = new ModelAndView("redirect:/sell/list-product");
        Product product = productService.findProduct(id);
        product.setActive("2");
        redirectAttributes.addFlashAttribute("active", "2");
        productService.save(product);
        return modelAndView;
    }

    @GetMapping(value = "/list-sell/{id}")
    public ModelAndView listSell(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        ModelAndView modelAndView = new ModelAndView("redirect:/sell/list-product");
        Product product = productService.findProduct(id);
        product.setActive("1");
        productService.save(product);
        redirectAttributes.addFlashAttribute("active", "3");
        productService.save(product);
        return modelAndView;
    }

    @Autowired
    StatisticsByProductService statisticsByProductService;
    @Autowired
    StatisticsByQuantityOfCategoryService byQuantityOfCategoryService;

    @GetMapping(value = "/product-statistics")
    public ModelAndView producSstatistics(@ModelAttribute("seller-session") Seller se) {
        ModelAndView modelAndView = new ModelAndView("/sell/product-statistics");
        List<StatisticsByProduct> statisticsByProducts = (List<StatisticsByProduct>) statisticsByProductService.statisticsByProduct(se.getId());
        List<StatisticsByQuantityOfCategory> quantityOfCategories = (List<StatisticsByQuantityOfCategory>) byQuantityOfCategoryService.StatisticsByQuantityOfCategory(se.getId());
        List<OrderConfirmation> listOrderWait = (List<OrderConfirmation>) orderConfirmationService.listOrderWait(se.getId());
        modelAndView.addObject("listOrderWait", listOrderWait.size());
        List<OrderConfirmation> listOrderConfirmed = (List<OrderConfirmation>) orderConfirmationService.listOrderConfirmed(se.getId());
        modelAndView.addObject("listOrderConfirmed", listOrderConfirmed.size());
        List<OrderConfirmation> listOrderRefuse = (List<OrderConfirmation>) orderConfirmationService.listOrderRefuse(se.getId());
        modelAndView.addObject("listOrderRefuse", listOrderRefuse.size());
        int sumOrder = listOrderConfirmed.size() + listOrderRefuse.size() + listOrderWait.size();
        modelAndView.addObject("sumOrder", sumOrder);
        modelAndView.addObject("quantityOfCategories", quantityOfCategories);
        modelAndView.addObject("statisticsByProducts", statisticsByProducts);
        int sumQuantity = 0;
        double sumbycategory = 0;
        for (int i = 0; i < quantityOfCategories.size(); i++) {
            sumQuantity = sumQuantity + quantityOfCategories.get(i).getSumquantity();
            sumbycategory = sumbycategory + quantityOfCategories.get(i).getSumbycategory();
            if (quantityOfCategories.get(i).getId() == 1) {
                modelAndView.addObject("quan_ao", quantityOfCategories.get(i).getSumquantity());
                modelAndView.addObject("quan_ao_2", quantityOfCategories.get(i).getSumbycategory());

            }
            if (quantityOfCategories.get(i).getId() == 2) {
                modelAndView.addObject("tui_xach", quantityOfCategories.get(i).getSumquantity());
                modelAndView.addObject("tui_xach_2", quantityOfCategories.get(i).getSumbycategory());

            }
            if (quantityOfCategories.get(i).getId() == 3) {
                modelAndView.addObject("giay_dep", quantityOfCategories.get(i).getSumquantity());
                modelAndView.addObject("giay_dep_2", quantityOfCategories.get(i).getSumbycategory());

            }
            if (quantityOfCategories.get(i).getId() == 4) {
                modelAndView.addObject("dien_tu", quantityOfCategories.get(i).getSumquantity());
                modelAndView.addObject("dien_tu_2", quantityOfCategories.get(i).getSumbycategory());

            }
            if (quantityOfCategories.get(i).getId() == 5) {
                modelAndView.addObject("khac", quantityOfCategories.get(i).getSumquantity());
                modelAndView.addObject("khac_2", quantityOfCategories.get(i).getSumbycategory());

            }
        }
        double sum = 0;
        for (int i = 0; i < statisticsByProducts.size(); i++) {
            sum = sum + statisticsByProducts.get(i).getSum();
        }
        modelAndView.addObject("sum", sum);
        modelAndView.addObject("sumQuantity", sumQuantity);
        modelAndView.addObject("sumbycategory", sumbycategory);

        return modelAndView;
    }

    @GetMapping(value = "/logup")
    public ModelAndView logup() {
        ModelAndView modelAndView = new ModelAndView("sell/logup");
        modelAndView.addObject("seller", new Seller());
        return modelAndView;
    }

    @PostMapping(value = "/check-logup")
    public ModelAndView checkLogup(@ModelAttribute("seller") Seller seller, RedirectAttributes redirectAttributes) {
        ModelAndView modelAndView;
        String account = seller.getAccount();
        List<Seller> sellerList = (List<Seller>) sellerService.findAll();
        for (int i = 0; i < sellerList.size(); i++) {
            if (account.equals(sellerList.get(i).getAccount())) {
                modelAndView = new ModelAndView("sell/logup");
                modelAndView.addObject("seller", seller);
                modelAndView.addObject("messenger", "Tài khoản đã tồn tại");
                return modelAndView;
            }
        }
        sellerService.save(seller);
        modelAndView = new ModelAndView("redirect:/sell/login");
        redirectAttributes.addFlashAttribute("account", account);
        redirectAttributes.addFlashAttribute("password", seller.getPassword());
        return modelAndView;
    }


}
