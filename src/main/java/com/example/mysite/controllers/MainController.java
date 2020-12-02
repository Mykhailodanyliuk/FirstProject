package com.example.mysite.controllers;

import com.example.mysite.models.Product;
import com.example.mysite.repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@Controller
public class MainController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/")
    public String Page(Model model){
        Iterable<Product> products = productRepository.findAll();
        model.addAttribute("products",products);
        return "mainPage.html";
    }

    @PostMapping("/{id}/remove")
    public String deleteProduct(@PathVariable(value = "id") long id, Model model) {
        Product product = productRepository.findById(id).orElseThrow();
        productRepository.delete(product);
        return "redirect:/";
    }

    @GetMapping("/login")
    public String LoginPage(Map<String,Object> model){
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        if (!(auth instanceof AnonymousAuthenticationToken))
//            return "redirect:/";
//
        return "login";
    }

    @GetMapping("/addProduct")
    public String add(Model model){
        return "addProduct.html";
    }

    @PostMapping("/addProduct")
    public String addProduct(@RequestParam String name, @RequestParam String typeProduct, @RequestParam int price, Model model) {

        Product product = new Product(name,typeProduct,price);
        productRepository.save(product);
        return "redirect:/";
    }


            }
