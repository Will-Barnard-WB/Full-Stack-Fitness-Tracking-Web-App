package com.stravacopy.backend.Model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class Mood {
    private int mood;
    private Date date;

    public Mood(int mood) {
        this.mood = mood;
        this.date = new Date();
    }

    // Getter for mood field
    public int getMood() {
        return mood;
    }

    // Setter for mood field
    public void setMood(int mood) {
        this.mood = mood;
    }

    public Date getDate()
    {
        return date;
    }

    public void setDate(Date date)
    {
        this.date = date;
    }

}

