package com.example.user_service.controller;

import com.example.user_service.dto.PreferencesDTO;
import com.example.user_service.model.User;
import com.example.user_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

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
    public ResponseEntity<String> updatePreferences(
            @PathVariable Long userId,
            @RequestBody PreferencesDTO newPreferences){
        userService.updateUserPreferences(userId, newPreferences);
        return ResponseEntity.ok("User preferences updated");
    }

    @GetMapping("/{userId}/preferences")
    public PreferencesDTO getPreferences(@PathVariable Long userId) {
        return userService.getUserPreferences(userId);
    }

}
