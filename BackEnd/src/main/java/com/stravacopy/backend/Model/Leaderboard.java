package com.stravacopy.backend.Model;

import java.util.List;

public class Leaderboard {

    List<LeaderboardEntry> entries;

    public Leaderboard(List<LeaderboardEntry> entries) {
        this.entries = entries;
    }

    public List<LeaderboardEntry> getEntries()
    {
        return entries;
    }

    public void setEntries(List<LeaderboardEntry> entries)
    {
        this.entries = entries;
    }

    @Override
    public String toString()
    {
        return "Leaderboard{" +
                "entries=" + entries +
                '}';
    }
}