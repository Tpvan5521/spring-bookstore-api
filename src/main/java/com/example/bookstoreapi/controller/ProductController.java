package com.example.bookstoreapi.controller;

import com.example.bookstoreapi.entity.Product;
import com.example.bookstoreapi.service.ProductService;
import java.util.List;
import java.util.concurrent.ExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ProductController {
    
    @Autowired
    private ProductService productService;
    
    @GetMapping("/products")
    public List<Product> getProducts() throws ExecutionException, InterruptedException{
        return productService.getProducts();
    }
    
    @GetMapping("/products/{FIELD}/{value}")
    public List<Product> getProductsByValue(@PathVariable String FIELD, @PathVariable String value) throws ExecutionException, InterruptedException{
        return productService.getProductsByValue(FIELD, value);
    }
    
    @GetMapping("/products/{FIELD}/{min}/{max}")
    public List<Product> getProductsByRange(String FIELD, int min, int max) throws ExecutionException, InterruptedException{
        return productService.getProductsByRange(FIELD, min, max);
    }
    
    @GetMapping("/products/{FIELD}/{limitValue}")
    public List<Product> getProductsOrderedAndLimited(String FIELD, int limitValue) throws ExecutionException, InterruptedException{
        return productService.getProductsOrderedAndLimited(FIELD, limitValue);
    }
    
    @GetMapping("/products/{productSlug}")
    public Product getProductDetails(@PathVariable String productSlug) throws ExecutionException, InterruptedException{
        return productService.getProductDetails(productSlug);
    }
    
    @PostMapping("/products")
    public String createProduct(@RequestBody Product product) throws ExecutionException, InterruptedException{
        return productService.createProduct(product);
    }
    
    @PutMapping("/products/{productSlug}")
    public String updateProduct(@PathVariable String productSlug, @RequestBody Product product) throws ExecutionException, InterruptedException{
        return productService.updateProduct(productSlug, product);
    }
    
    @DeleteMapping("/products/{productSlug}")
    public String deleteProduct(@PathVariable String productSlug) throws ExecutionException, InterruptedException{
        return productService.deleteProduct(productSlug);
    }
}
