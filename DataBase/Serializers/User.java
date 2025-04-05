package Serializers;

import java.util.List;

public class User {

    private final int id;
    private String name;
    private final List<Workout> workouts;

    public User(int id, String Name, List<Workout> workouts) {
        this.id = id;
        this.name = Name;
        this.workouts = workouts;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Workout> getWorkouts() {
        return workouts;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", workouts=" + workouts +
                '}';
    }
}
