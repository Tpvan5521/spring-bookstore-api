package com.example.bookstoreapi.controller;

import com.example.bookstoreapi.entity.CartItem;
import com.example.bookstoreapi.service.CartItemService;
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
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api")
public class CartItemController {
    @Autowired
    private CartItemService cartItemService;
    
    @GetMapping("/carts/{userId}")
    public List<CartItem> getCartItems(@PathVariable String userId) throws ExecutionException, InterruptedException{
        return cartItemService.getCartItems(userId);
    }
    
    @PostMapping("/carts")
    public String createCartItem(@RequestBody CartItem cartItem) throws ExecutionException, InterruptedException{
        return cartItemService.createCartItem(cartItem);
    }
    
    @PutMapping("/carts/{userId}/{productId}")
    public String updateCartItem(@PathVariable String userId, @PathVariable String productId, @RequestBody CartItem cartItem) throws ExecutionException, InterruptedException{
        if(cartItem.getQuantity() <= 0) {
            return cartItemService.deleteCartItem(userId, productId);
        }
        return cartItemService.updateCartItem(userId, productId, cartItem);
    }
    
    @DeleteMapping("/carts/{userId}/{productId}")
    public String deleteProduct(@PathVariable String userId, @PathVariable String productId) throws ExecutionException, InterruptedException{
        return cartItemService.deleteCartItem(userId, productId);
    }
}
