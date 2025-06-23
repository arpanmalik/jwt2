package com.example.demo2PractiseSecurity.service;

import com.example.demo2PractiseSecurity.models.Product;
import com.example.demo2PractiseSecurity.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductRepo repo;

    public Object save(Product product, String username) {
        product.setCreatedBy(username);
        return repo.save(product);
    }
}
