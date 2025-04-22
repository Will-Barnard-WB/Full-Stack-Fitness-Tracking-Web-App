package com.stravacopy.backend.Controller;

import com.stravacopy.backend.Model.*;
import com.stravacopy.backend.Repository.UserRepository;
import com.stravacopy.backend.Service.MoodService;
import com.stravacopy.backend.Service.UserService;
import com.stravacopy.backend.Service.StatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users") // Base URL for user-related requests
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private StatsService statsService;

    @Autowired
    private MoodService moodService;

    // Simple endpoint to get all users
    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void createUser()
    {
        User user = new User();

        user.setId("4");
        user.setName("Aaron");
        user.password = "hello";
        List<Mood> moods = new ArrayList<>();
        List<Workout> workouts = new ArrayList<>();
        Workout workout = new Workout("4");
        workouts.add(workout);
        user.setMoods(moods);
        user.setWorkouts(workouts);
        addUser(user);
        System.out.println(getAllUsers());
    }

    public User addUser(User user)
    {

        return userRepository.save(user);
    }

    public User getUserById(String id)
    {
        return userRepository.findById(id);
    }

    public User getUserByName(String name)
    {
        return userRepository.findByName(name);
    }

    @GetMapping("/{userId}/workouts/{workoutId}") //not sure on the path here
    public ResponseEntity<RunningStats> getWorkoutStats(
            @PathVariable String userId,
            @PathVariable String workoutId) {
        Workout workout = userService.getWorkoutByUserIdAndWorkoutId(userId, workoutId);
        System.out.println(workout);
        if (workout != null) {
            RunningStats workoutStats = statsService.generateWorkOutStats(workout);
            return ResponseEntity.ok(workoutStats);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{userId}/workouts") // again not sure on paths
    public ResponseEntity<RunningStats> getAllWorkoutStats(@PathVariable String userId) {
        List<Workout> workouts = userService.getAllWorkoutsForUser(userId);
        if (!workouts.isEmpty()) {
            RunningStats userStats = statsService.generateUserStats(workouts);
            return ResponseEntity.ok(userStats);
        }
        return ResponseEntity.ok((RunningStats) workouts);
    }

    @PostMapping("/{userId}/mood")
    public ResponseEntity<String> getUserMood(@PathVariable String userId, @RequestBody int moodValue) {
        User user = userService.getUserByID(userId);
        if (user != null){
            Mood mood = new Mood(moodValue);
            List<Mood> currentMoods = user.getMoods();
            currentMoods.add(mood);
            user.setMoods(currentMoods);
            addUser(user);
            return ResponseEntity.ok("Mood saved successfully.");
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }






}
