package com.example.bookstoreapi.controller;

import com.example.bookstoreapi.entity.CartItem;
import com.example.bookstoreapi.service.CartItemService;
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
public class CartItemController {
    @Autowired
    private CartItemService cartItemService;
    
    @GetMapping("/cart/{userId}")
    public List<CartItem> getCartItem(@PathVariable String userId) throws ExecutionException, InterruptedException{
        return cartItemService.getCartItem(userId);
    }
    
    @PostMapping("/cart")
    public String createCartItem(@RequestBody CartItem cartItem) throws ExecutionException, InterruptedException{
        return cartItemService.createCartItem(cartItem);
    }
    
    @PutMapping("/cart/{productId}")
    public String updateCartItem(@PathVariable String productId, @RequestBody CartItem cartItem) throws ExecutionException, InterruptedException{
        if(cartItem.getQuantity() <= 0) {
            return cartItemService.deleteCartItem(productId);
        }
        return cartItemService.updateCartItem(productId, cartItem);
    }
    
    @DeleteMapping("/cart/{productId}")
    public String deleteProduct(@PathVariable String productId) throws ExecutionException, InterruptedException{
        return cartItemService.deleteCartItem(productId);
    }
}
