package com.assignment.nl22w;

import com.assignment.nl22w.game.Game;
import com.assignment.nl22w.game.impl.GameImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;

@SpringBootApplication
public class GameApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(GameApplication.class, args);

		Game game = context.getBean(GameImpl.class);

		Resource resource = new ClassPathResource("map1.txt");

		try {
			game.escapeFromTheWoods(resource);
		} catch (IOException e) {
			System.err.println("Error occurred while running the game: " + e.getMessage());
		}

		context.close();
	}
}
