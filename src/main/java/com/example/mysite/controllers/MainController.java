package com.example.mysite.controllers;

import com.example.mysite.models.Product;
import com.example.mysite.repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.*;


@Controller
public class MainController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/")
    public String Page(Model model){
        Iterable<Product> products = productRepository.findAll();
        model.addAttribute("products",products);
        return "mainPage";
    }

    @PostMapping("/{id}/remove")
    public String deleteProduct(@PathVariable(value = "id") long id, Model model) {
        Product product = productRepository.findById(id).orElseThrow();
        productRepository.delete(product);
        return "redirect:/";
    }

    @GetMapping("/addProduct")
    public String add(Model model){
        return "addProduct";
    }

    @PostMapping("/addProduct")
    public String addProduct(@RequestParam String name, @RequestParam String typeProduct, @RequestParam int price, Model model) {

        Product product = new Product(name,typeProduct,price);
        productRepository.save(product);
        return "redirect:/";
    }


            }
