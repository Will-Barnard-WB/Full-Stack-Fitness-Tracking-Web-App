package com.stravacopy.backend;

import com.stravacopy.backend.Controller.MainController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLOutput;

@SpringBootApplication
public class BackEndApplication implements CommandLineRunner {

	@Autowired
	private MainController mainController;

	public static void main(String[] args) {

		SpringApplication.run(BackEndApplication.class, args);

	}

	@Override
	public void run(String... args) throws Exception{
		System.out.println(mainController.getUserData("2"));
		System.out.println(mainController.getLeaderboard("fastest"));
		mainController.addUserMood("1", 5);
	}

}
