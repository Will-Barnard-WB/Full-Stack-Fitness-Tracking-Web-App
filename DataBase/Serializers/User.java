package Serializers;

import java.util.List;

public class User {

    private final int id;
    private String name;
    private final List<Workout> workouts;
    private final List<Mood> moods;

    public User(int id, String Name, List<Workout> workouts, List<Mood> moods) {
        this.id = id;
        this.name = Name;
        this.workouts = workouts;
        this.moods = moods;
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

    public List<Mood> getMoods() {
        return moods;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", workouts=" + workouts +
                ", moods=" + moods +
                '}';
    }
}
