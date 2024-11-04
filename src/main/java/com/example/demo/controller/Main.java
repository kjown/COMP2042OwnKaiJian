package com.example.demo.controller;

import javafx.application.Application;
import javafx.stage.Stage;
import com.example.demo.StartMenu;

public class Main extends Application {
	private static final int SCREEN_WIDTH = 1300;
	private static final int SCREEN_HEIGHT = 750;
	private Controller controller;

	@Override
	public void start(Stage stage) {
		stage.setTitle("Sky Battle"); // Set the title of the window

		controller = new Controller(stage, SCREEN_WIDTH, SCREEN_HEIGHT);

		// Create and display the StartMenu
		StartMenu startMenu = new StartMenu(stage, SCREEN_WIDTH, SCREEN_HEIGHT, controller);
		startMenu.show();

		// Show the stage
		stage.show();
	}

	public static void main(String[] args) {
		launch();
	}

	public static int getScreenWidth() {
		return SCREEN_WIDTH;
	}
}