package com.stravacopy.backend.Model;

public class LeaderboardEntry {
    //generic entry for leaderboards

    private String username;
    private double value;

    public LeaderboardEntry(String username, double value) {
        this.username = username;
        this.value = value;
    }

    public String getUsername() {
        return username;
    }

    public double getValue() {
        return value;
    }
}