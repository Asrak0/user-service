package com.example.user_service.controller;

import com.example.user_service.dto.Preferences;
import com.example.user_service.model.User;
import com.example.user_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/register")
    public User registerUser(@RequestBody User user){
        return userService.registerUser(user);
    }

    @GetMapping("/{username}")
    public User getUser(@PathVariable String username){
        return userService.findByUserName(username);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable Long userId) {

        userService.deleteUser(userId);
        return ResponseEntity.ok("User has been deleted");
    }

    @PutMapping("/{userId}/preferences")
    public ResponseEntity<String> updatePreferences(@PathVariable Long userId, @RequestBody Preferences newPreferences){
        userService.updateUserPreferences(userId, newPreferences);
        return ResponseEntity.ok("User preferences updated");
    }

    @GetMapping("/{userId}/preferences")
    public Preferences getPreferences(@PathVariable Long userId) {
        return userService.getUserPreferences(userId);
    }

}
