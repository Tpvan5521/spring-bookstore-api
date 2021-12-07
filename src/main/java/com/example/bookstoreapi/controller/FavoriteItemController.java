package com.example.bookstoreapi.controller;

import com.example.bookstoreapi.entity.FavoriteItem;
import com.example.bookstoreapi.service.FavoriteItemService;
import java.util.List;
import java.util.concurrent.ExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class FavoriteItemController {
    @Autowired
    private FavoriteItemService favoriteItemService;
    
    @GetMapping("/favorites/{userId}")
    public List<FavoriteItem> getFavoriteItems(@PathVariable String userId) throws ExecutionException, InterruptedException{
        return favoriteItemService.getFavoriteItems(userId);
    }
    
    @PostMapping("/favorites")
    public String createFavoriteItem(@RequestBody FavoriteItem favoriteItem) throws ExecutionException, InterruptedException{
        return favoriteItemService.createFavoriteItem(favoriteItem);
    }
    
    @DeleteMapping("/favorites/{userId}/{productId}")
    public String deleteProduct(@PathVariable String userId, @PathVariable String productId) throws ExecutionException, InterruptedException{
        return favoriteItemService.deleteFavoriteItem(userId, productId);
    }
}
