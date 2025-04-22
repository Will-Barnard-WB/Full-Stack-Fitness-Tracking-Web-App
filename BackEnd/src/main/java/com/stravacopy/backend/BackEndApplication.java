package com.stravacopy.backend;

import com.stravacopy.backend.Controller.UserController;
import com.stravacopy.backend.Model.Mood;
import com.stravacopy.backend.Model.Workout;
import com.stravacopy.backend.Repository.UserRepository;
import com.stravacopy.backend.Model.User;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class BackEndApplication implements CommandLineRunner {

	@Autowired
	private UserController userController;

	public static void main(String[] args) {

		SpringApplication.run(BackEndApplication.class, args);

	}

	@Override
	public void run(String... args) throws Exception{
		userController.createUser();
		System.out.println(userController.getWorkoutStats("3", "3"));
	}

}
