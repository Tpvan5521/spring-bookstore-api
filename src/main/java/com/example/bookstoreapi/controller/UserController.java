package com.example.bookstoreapi.controller;

import com.example.bookstoreapi.entity.User;
import com.example.bookstoreapi.service.UserService;
import java.util.concurrent.ExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService userService;
    
    @GetMapping("/users/{userSlug}")
    public User getUser(@PathVariable String userSlug) throws ExecutionException, InterruptedException{
        return userService.getUser(userSlug);
    }
    
    @PostMapping("/users")
    public String createUser(@RequestBody User user) throws ExecutionException, InterruptedException{
        return userService.createUser(user);
    }
    
    @PutMapping("/users/{userSlug}")
    public String updateUser(@PathVariable String userSlug, @RequestBody User user) throws ExecutionException, InterruptedException{
        return userService.updateUser(userSlug, user);
    }
}
