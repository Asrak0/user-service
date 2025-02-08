package com.example.user_service.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String userName;

    @Column(nullable = false)
    private String userPassword;

    @Column(nullable = false, unique = true)
    private String emailId;

    private String preferences;

//    Non-owning side of the relationship marked by "mappedBy"
//    cascade = CascadeType.ALL makes sure saving any watch history
//    to this list will also update the watchHistory entry in watchHistory repository. WIN

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<WatchHistory> watchHistory;

    public User() {
    }

    public User(Long id, String userName, String userPassword, String emailId, String preferences, List<WatchHistory> watchHistory) {
        this.id = id;
        this.userName = userName;
        this.userPassword = userPassword;
        this.emailId = emailId;
        this.preferences = preferences;
        this.watchHistory = new ArrayList<>();
    }

//    Helper method to better maintain bidirectional relationship and update the watchHistory list on user side
//    We can use this method to add watchHistory to users list and at the same time set the user in watchHistory
//    entry which is the owning side of the relationship

    public void addWatchHistory(WatchHistory watchHistory){
        watchHistory.setUser(this);
        this.watchHistory.add(watchHistory);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getPreferences() {
        return preferences;
    }

    public void setPreferences(String preferences) {
        this.preferences = preferences;
    }

    public List<WatchHistory> getWatchHistory() {
        return watchHistory;
    }

    public void setWatchHistory(List<WatchHistory> watchHistory) {
        this.watchHistory = watchHistory;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }


}
