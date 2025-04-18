package com.stravacopy.backend.Controller;

import com.stravacopy.backend.Model.User;
import com.stravacopy.backend.Model.Workout;
import com.stravacopy.backend.Repository.UserRepository;
import com.stravacopy.backend.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users") // Base URL for user-related requests
public class UserController {

    @Autowired
    private UserRepository userRepository;
    private UserService userService;

    // Simple endpoint to get all users
    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User addUser(User user)
    {
        return userRepository.save(user)
    }

    public User getUserById(int id)
    {
        return userRepository.findById(id)
    }

    public User getUserByName(string name)
    {
        return userRepository.findByName(name)
    }

    @GetMapping("/{userId}/workouts/{workoutId}") //not sure on the path here
    public ResponseEntity<Workout> getWorkoutByIds(
            @PathVariable String userId,
            @PathVariable String workoutId) {
        Workout workout = userService.getWorkoutByUserIdAndWorkoutId(userId, workoutId);
        if (workout != null) {
            return ResponseEntity.ok(workout);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{userId}/workouts") // again not sure on paths
    public ResponseEntity<List<Workout>> getAllWorkouts(@PathVariable String userId) {
        List<Workout> workouts = userService.getAllWorkoutsForUser(userId);
        return ResponseEntity.ok(workouts);
    }



}
