package com.example.demo2PractiseSecurity.repo;

import com.example.demo2PractiseSecurity.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo2PractiseSecurity.models.Product;

public interface ProductRepo extends JpaRepository<Product, Integer> {

}
