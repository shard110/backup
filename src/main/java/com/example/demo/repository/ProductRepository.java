package com.example.demo.repository;

import com.example.demo.entity.Product;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findByPCate(int pCate);
}
