package com.example.user_service.service;

import com.example.user_service.dto.PreferencesDTO;
import com.example.user_service.model.User;
import com.example.user_service.repository.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private final UserRepository userRepository;
    private final ObjectMapper objectMapper;

    public UserService(UserRepository userRepository, ObjectMapper objectMapper){
        this.userRepository = userRepository;
        this.objectMapper = objectMapper;
    }

    public User registerUser(User user){
        return userRepository.save(user);
    }

    public User findByUserName(String userName){
        return userRepository.findByUserName(userName);
    }

    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }

//    updating user preferences by converting PreferencesDTO object into JSON string -> setting JSON representation
//    of preferences to user and then updating user using repository method.
    public void updateUserPreferences(Long userId, PreferencesDTO newPreferences){
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isEmpty()){
            throw new RuntimeException("User not found");
        }

        User user = optionalUser.get();

        try {
            String jsonPreferences = objectMapper.writeValueAsString(newPreferences);
            user.setPreferences(jsonPreferences);
            userRepository.save(user);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to convert preferences to JSON");
        }

    }

//    returning JSON preferences from user in the form of PreferencesDTO object
    public PreferencesDTO getUserPreferences(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isEmpty()){
            throw new RuntimeException("User not found");
        }

        User user = optionalUser.get();
        try {
            return objectMapper.readValue(user.getPreferences(), PreferencesDTO.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to parse JSON preferences", e);
        }
    }

}
