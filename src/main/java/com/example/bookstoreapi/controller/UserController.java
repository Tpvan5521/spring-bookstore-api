package com.example.bookstoreapi.controller;

import com.example.bookstoreapi.entity.User;
import com.example.bookstoreapi.service.UserService;
import java.util.concurrent.ExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
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
public class UserController {
    @Autowired
    private UserService userService;
    
    @GetMapping("/users/user-id-of/{UID}")
    public String getUserId(@PathVariable String UID) throws ExecutionException, InterruptedException{
        return userService.getUserId(UID);
    }
    
    @GetMapping("/users/doc-id/{docId}")
    public User getUserByDocId(@PathVariable String docId) throws ExecutionException, InterruptedException{
        return userService.getUserByDocId(docId);
    }
    
    @GetMapping("/users/{UID}")
    public User getUser(@PathVariable String UID) throws ExecutionException, InterruptedException{
        return userService.getUser(UID);
    }
    
    @PostMapping("/users")
    public String createUser(@RequestBody User user) throws ExecutionException, InterruptedException{
        return userService.createUser(user);
    }
    
    @PutMapping("/users/{UID}")
    public String updateUser(@PathVariable String UID, @RequestBody User user) throws ExecutionException, InterruptedException{
        return userService.updateUser(UID, user);
    }
}
