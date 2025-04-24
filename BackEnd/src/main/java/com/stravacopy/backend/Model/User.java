package com.stravacopy.backend.Model;

//import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;


@Document(collection = "UserDataCollection")
public class User {
    @Id
    private String id;
    private String name;
    public String password;
    private List<Workout> workouts;
    private List<RunningStats> workoutStats;
    private List<Mood> moods;
    private RunningStats userStatistics;
    // Add further attributes as needed

    public User()
    {
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public void setPasssword(String password){
        this.password = password;
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

    public List<RunningStats> getWorkoutStats(){
        return workoutStats;
    }

    public void setWorkoutStats(List<RunningStats> workoutStats){
        this.workoutStats = workoutStats;
    }

    // Getters, setters, constructors

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", workouts=" + workouts +
                ", moods=" + moods +
                ", userStatistics=" + userStatistics +
                '}';
    }
}