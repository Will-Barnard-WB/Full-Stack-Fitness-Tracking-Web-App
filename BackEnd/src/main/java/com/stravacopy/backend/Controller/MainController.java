package com.stravacopy.backend.Controller;

import com.stravacopy.backend.Model.*;
import com.stravacopy.backend.Repository.UserRepository;
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
public class MainController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private StatsService statsService;

    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void createUser()
    {
        // USED FOR US TO MANUALLY ADD USERS TO DATABASE
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

    // GET THE INDIVIUDAL WORKOUT STATS STORED WITHIN USER OBJECT?????

    @GetMapping("/{userId}/workouts/{workoutId}")
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


    @GetMapping("/{userId}/data")
    public ResponseEntity<User> getUserData(@PathVariable String userId){
        User user = userService.getUserByID(userId);
        List<Workout> workouts = userService.getAllWorkoutsForUser(userId);
        if (!workouts.isEmpty()) {
            RunningStats userStats = statsService.generateUserStats(workouts);
            user.setUserStatistics(userStats);
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.ok(new User()); // NOT SURE WHAT TO RETURN?

    }

    @PostMapping("/{userId}/mood")
    public ResponseEntity<String> addUserMood(@PathVariable String userId, @RequestBody int moodValue) {
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

    @GetMapping("/Leaderboard")
    public ResponseEntity<Leaderboard> getLeaderboard(@RequestBody String LeaderboardType) {
        if (userRepository.findAll().isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(statsService.getLeaderboardByType(LeaderboardType, userRepository.findAll()));
        }
    }






}
