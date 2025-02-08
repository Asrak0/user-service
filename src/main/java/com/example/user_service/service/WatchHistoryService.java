package com.example.user_service.service;

import com.example.user_service.model.User;
import com.example.user_service.model.WatchHistory;
import com.example.user_service.repository.UserRepository;
import com.example.user_service.repository.WatchHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class WatchHistoryService {

    @Autowired
    private WatchHistoryRepository watchHistoryRepository;
    @Autowired
    private UserRepository userRepository;

    public void addToWatchHistory(Long userId, Long movieId){
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        WatchHistory watchHistory = new WatchHistory();
        watchHistory.setMovieId(movieId);
        watchHistory.setWatchedAt(LocalDateTime.now());

//      using helper method to maintain relationships
        user.addWatchHistory(watchHistory);

//      Saving to User rep. and with cascade all we also save to WatchHistory Rep. WIN
        userRepository.save(user);
    }

    public void removeWatchHistory(Long watchHistoryId){
        WatchHistory watchHistory = watchHistoryRepository.findById(watchHistoryId)
                .orElseThrow(() -> new RuntimeException("Watch History entry does not exist"));

        watchHistoryRepository.deleteById(watchHistoryId);
    }

    public List<WatchHistory> getWatchHistory(Long userId){
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        return user.getWatchHistory();
    }
}
