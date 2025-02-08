package com.example.user_service.controller;

import com.example.user_service.model.WatchHistory;
import com.example.user_service.service.WatchHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users/{userId}/watch-history")
public class WatchHistoryController {
    @Autowired
    private WatchHistoryService watchHistoryService;

    @PostMapping("/{movieId}")
    public void addWatchHistory(@PathVariable Long userId, @PathVariable Long movieId){
        watchHistoryService.addToWatchHistory(userId, movieId);
    }

    @DeleteMapping("/{watchHistoryId}")
    public void deleteWatchHistory(@PathVariable Long watchHistoryId){
        watchHistoryService.removeWatchHistory(watchHistoryId);
    }

    @GetMapping
    public List<WatchHistory> getWatchHistory(@PathVariable Long userId) {
        return watchHistoryService.getWatchHistory(userId);
    }
}
