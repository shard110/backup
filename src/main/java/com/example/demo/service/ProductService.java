package com.example.demo.service;

import com.example.demo.entity.Product;
import com.example.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(int pnum) {
        return productRepository.findById(pnum)
            .orElseThrow(() -> new RuntimeException("Product not found with id: " + pnum));
    }
    
    public List<Product> getProductsByCategory(int categoryId) {
        return productRepository.findByPCate(categoryId);
    }

    public Product saveOrUpdateProduct(Product product) {
        return productRepository.save(product);
    }

    public void deleteProduct(int pnum) {
        productRepository.deleteById(pnum);
    }
}
