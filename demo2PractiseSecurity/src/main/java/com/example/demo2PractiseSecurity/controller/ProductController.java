package com.example.demo2PractiseSecurity.controller;

import com.example.demo2PractiseSecurity.models.Product;
import com.example.demo2PractiseSecurity.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("add-product")
    public Object addProduct(@RequestBody Product product, Principal principal){
        String username = principal.getName();
        return productService.save(product, username);
    }
}
