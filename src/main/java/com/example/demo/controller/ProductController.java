package com.example.demo.controller;

import com.example.demo.entity.Product;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("/api/products")
@CrossOrigin("http://localhost:3000")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{pnum}")
    public Product getProductById(@PathVariable("pnum") int pnum) {
        return productService.getProductById(pnum);
    }

    @GetMapping("/category/{categoryId}")
    public List<Product> getProductsByCategory(@PathVariable("categoryId") int categoryId) {
        return productService.getProductsByCategory(categoryId);
    }

    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return productService.saveOrUpdateProduct(product);
    }

    @PutMapping("/{pnum}")
    public Product updateProduct(@PathVariable("pnum") int pnum, @RequestBody Product product) {
        product.setPnum(pnum);
        return productService.saveOrUpdateProduct(product);
    }

    @DeleteMapping("/{pnum}")
    public void deleteProduct(@PathVariable("pnum") int pnum) {
        productService.deleteProduct(pnum);
    }

    @PostMapping("/{pnum}/uploadImage")
    public Product uploadImage(@PathVariable("pnum") int pnum, @RequestParam("file") MultipartFile file) throws IOException {
        Product product = productService.getProductById(pnum);
        if (product == null) {
            throw new RuntimeException("Product not found with id: " + pnum);
        }

        // 파일 시스템에 이미지 저장
        String fileName = file.getOriginalFilename();
        String filePath = "path/to/save/images/" + fileName;
        File dest = new File(filePath);
        file.transferTo(dest);

        // 이미지 URL 설정
        product.setPImgUrl("/images/" + fileName);
        return productService.saveOrUpdateProduct(product);
    }

    @PostMapping("/{pnum}/uploadDetailImage")
    public Product uploadDetailImage(@PathVariable("pnum") int pnum, @RequestParam("file") MultipartFile file) throws IOException {
        Product product = productService.getProductById(pnum);
        if (product == null) {
            throw new RuntimeException("Product not found with id: " + pnum);
        }

        // 파일 시스템에 상세 이미지 저장
        String fileName = file.getOriginalFilename();
        String filePath = "path/to/save/images/" + fileName;
        File dest = new File(filePath);
        file.transferTo(dest);

        // 상세 이미지 URL 설정
        product.setPDetailImgUrl("/images/" + fileName);
        return productService.saveOrUpdateProduct(product);
    }
}
