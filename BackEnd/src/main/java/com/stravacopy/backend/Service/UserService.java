package com.stravacopy.backend.Service;

import com.stravacopy.backend.Model.User;
import com.stravacopy.backend.Model.Workout;
import com.stravacopy.backend.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Workout getWorkoutByUserIdAndWorkoutId(int userId, int workoutId) {
        Optional<User> optionalUser = Optional.ofNullable(userRepository.findById(userId));
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            return user.getWorkouts().stream()
                    .filter(workout -> workout.getId() == workoutId)
                    .findFirst()
                    .orElse(null);
        }
        return null;
    }

    public List<Workout> getAllWorkoutsForUser(int userId) {
        Optional<User> optionalUser = Optional.ofNullable(userRepository.findById(userId));
        return optionalUser.map(User::getWorkouts).orElse(Collections.emptyList());
    }

    public User getUserByID(int userId) {
        Optional<User> userOptional = Optional.ofNullable(userRepository.findById(userId));
        return userOptional.orElse(null);
    }

    // You can also add methods like:
    // - addRunToUser()
    // - deleteRunFromUser()
    // - getAllUsers()
    // etc.
}
