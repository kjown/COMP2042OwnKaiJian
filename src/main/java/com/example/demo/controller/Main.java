package com.example.demo.controller;

import javafx.application.Application;
import javafx.stage.Stage;
import com.example.demo.StartMenu;

/**
 * Main class to launch the application "Sky Battle"
 */
public class Main extends Application {
	private static final int SCREEN_WIDTH = 1300;
	private static final int SCREEN_HEIGHT = 750;
	private Controller controller;

	/**
	 * Initializes and display the priamry stage with StartMenu
	 * @param stage the primary stage for this application, onto which
	 * the application scene can be set.
	 * Applications may create other stages, if needed, but they will not be
	 * primary stages.
	 */
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

	/**
	 * Launch the application
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		launch();
	}

	public static int getScreenWidth() {
		return SCREEN_WIDTH;
	}
}