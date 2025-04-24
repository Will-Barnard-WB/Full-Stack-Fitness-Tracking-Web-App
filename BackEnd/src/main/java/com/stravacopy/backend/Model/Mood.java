package com.stravacopy.backend.Model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Mood {
    private int mood;

    public Mood(int mood) {
        this.mood = mood;
    }

    // Getter for mood field
    public int getMood() {
        return mood;
    }

    // Setter for mood field
    public void setMood(int mood) {
        this.mood = mood;
    }



}

