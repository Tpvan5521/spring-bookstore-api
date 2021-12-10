package com.example.bookstoreapi.controller;

import com.example.bookstoreapi.entity.Product;
import com.example.bookstoreapi.service.ProductService;
import java.util.List;
import java.util.concurrent.ExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProductService productService;
    
    @GetMapping("/products/product-id-of/{productSlug}")
    public String getProductId(@PathVariable String productSlug) throws ExecutionException, InterruptedException{
        return productService.getProductId(productSlug);
    }
    
    @GetMapping("/products/doc-id/{docId}")
    public Product getProductByDocId(@PathVariable String docId) throws ExecutionException, InterruptedException{
        return productService.getProductByDocId(docId);
    }

    @GetMapping("/products")
    public List<Product> getProducts() throws ExecutionException, InterruptedException {
        return productService.getProducts();
    }

    @GetMapping("/products/{FIELD}/{value}") // ?type=...
    public List<Product> getProductsByValue(@PathVariable String FIELD, @PathVariable String value, @RequestParam String type) throws ExecutionException, InterruptedException {
        if ("number".equals(type) | "Number".equals(type) | "int".equals(type)) {
            try {
                int valueParseToNumber = Integer.parseInt(value);
                return productService.getProductsByValue(FIELD, valueParseToNumber);
            } catch (NumberFormatException e) {
            }
        }
        return productService.getProductsByValue(FIELD, value);
    }

    @GetMapping("/products/{FIELD}/{min}/{max}")
    public List<Product> getProductsByRange(String FIELD, int min, int max) throws ExecutionException, InterruptedException {
        return productService.getProductsByRange(FIELD, min, max);
    }

    @GetMapping("/products/order/{FIELD}/limit/{limitValue}")
    public List<Product> getProductsOrderedAndLimited(String FIELD, int limitValue) throws ExecutionException, InterruptedException {
        return productService.getProductsOrderedAndLimited(FIELD, limitValue);
    }

    @GetMapping("/products/{productSlug}")
    public Product getProductDetails(@PathVariable String productSlug) throws ExecutionException, InterruptedException {
        return productService.getProductDetails(productSlug);
    }

    @PostMapping("/products")
    public String createProduct(@RequestBody Product product) throws ExecutionException, InterruptedException {
        return productService.createProduct(product);
    }

    @PutMapping("/products/{productSlug}")
    public String updateProduct(@PathVariable String productSlug, @RequestBody Product product) throws ExecutionException, InterruptedException {
        return productService.updateProduct(productSlug, product);
    }

    @DeleteMapping("/products/{productSlug}")
    public String deleteProduct(@PathVariable String productSlug) throws ExecutionException, InterruptedException {
        return productService.deleteProduct(productSlug);
    }
}
