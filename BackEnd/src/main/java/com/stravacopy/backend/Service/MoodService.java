package com.stravacopy.backend.Service;

import com.stravacopy.backend.Model.Mood;
import org.springframework.stereotype.Service;

@Service
public class MoodService {


    public Mood saveMood(Mood mood) {
        return mood;
    }
    // add functions needed, like save to database.
    // update mood model, and mood repository to reflect this.
}
