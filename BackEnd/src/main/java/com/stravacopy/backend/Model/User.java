package com.stravacopy.backend.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;


@Document(collection = "UserDataCollection")
public class User {
    @Id
    private int id;
    private String name;
    private List<Workout> workouts;
    private List<Mood> moods;

    // Add further attributes as needed

    // Getters, setters, constructors
}