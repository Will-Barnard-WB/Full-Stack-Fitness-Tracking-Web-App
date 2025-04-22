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
    private RunningStats userStatistics;
    // Add further attributes as needed

    public User()
    {
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }





    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public List<Workout> getWorkouts()
    {
        return workouts;
    }


    public void setWorkouts(List<Workout> workouts)
    {
        this.workouts = workouts;
    }

    public List<Mood> getMoods()
    {
        return moods;
    }

    public void setMoods(List<Mood> moods)
    {
        this.moods = moods;
    }

    public RunningStats getUserStatistics()
    {
        return userStatistics;
    }

    public void setUserStatistics(RunningStats userStatistics)
    {
        this.userStatistics = userStatistics;
    }

    // Getters, setters, constructors
}