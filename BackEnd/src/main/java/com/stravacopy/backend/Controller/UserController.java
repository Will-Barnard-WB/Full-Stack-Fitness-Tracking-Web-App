package com.stravacopy.backend.Controller;

import com.stravacopy.backend.Model.User;
import com.stravacopy.backend.Model.Workout;
import com.stravacopy.backend.Repository.UserRepository;
import com.stravacopy.backend.Service.UserService;
import com.stravacopy.backend.Service.StatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    public User addUser(User user)
    {
        return userRepository.save(user);
    }

    public User getUserById(int id)
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
        return ResponseEntity.ok(workouts);
    }

    @PostMapping("/{userId}/mood") // again not sure on paths
    public ResponseEntity<Mood> getUserMood(@PathVariable String userId, @RequestBody int moodValue) {
        User user = userService.getUserById(userId);
        if (user != null){
            Mood mood = new Mood(user, moodValue); // change as needed based on chosen implementation
            Mood savedMood = moodService.saveMood(mood): // again change once implemented

            return ResponseEntity.ok(savedMood);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }



}
