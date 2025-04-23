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
import java.util.Optional;

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

    public void createUser() {

        List<Workout> workouts = new ArrayList<>();

        for (int i = 50; i < 75; i++) {

            CSVreader csvData = new CSVreader("/Users/willbarnard/Downloads/resources/factivity" + (i + 100) + "_metrics.csv", String.valueOf(i));
            Workout workout = csvData.getWorkout();
            workouts.add(workout);
        }

        User user = new User();
        user.setId("4");
        user.setName("Dylan");
        user.password ="123456789";
        List<Mood> moods = new ArrayList<>();
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


    @GetMapping("/{userId}/data")
    public ResponseEntity<User> getUserData(@PathVariable String userId){
        User user = userService.getUserByID(userId);
        List<Workout> workouts = userService.getAllWorkoutsForUser(userId);
        if (!workouts.isEmpty()) {
            Stats stats = statsService.generateUserStats(workouts);
            user.setUserStatistics(stats.getRunningStats());
            user.setWorkoutStats(stats.getAllWorkoutStats());
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

    @PostMapping("/{userId}/workouts")
    public ResponseEntity<String> addWorkout(@PathVariable String userId, @RequestBody Workout workout) {
        User user = userRepository.findById(userId);

        if (user != null) {

            List<Workout> workouts = user.getWorkouts();
            if (workouts == null) workouts = new ArrayList<>();
            workouts.add(workout);
            user.setWorkouts(workouts);
            userRepository.save(user);
            return ResponseEntity.ok("Workout added.");
        } else {
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
