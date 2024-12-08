package com.example.demo.controller;

import javafx.application.Application;
import javafx.stage.Stage;
import com.example.demo.menu.StartMenu;

/**
 * Main class to launch the application "Sky Battle".
 * This class is the entry point of the application, initializing the primary stage and displaying the start menu.
 */
public class Main extends Application {
	private static final int SCREEN_WIDTH = 1300;
	private static final int SCREEN_HEIGHT = 750;
	private Controller controller;

	/**
	 * Initializes and displays the primary stage with the StartMenu.
	 * This method is called by the JavaFX runtime when the application is launched.
	 *
	 * @param stage the primary stage for this application, onto which the application scene can be set.
	 *              Applications may create other stages if needed, but they will not be primary stages.
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
	 * Launches the application.
	 * This is the main entry point of the application that calls the `launch()` method
	 * to start the JavaFX application lifecycle.
	 *
	 * @param args the command line arguments (not used in this implementation)
	 */
	public static void main(String[] args) {
		launch();
	}

	/**
	 * Returns the width of the screen.
	 *
	 * @return the width of the screen in pixels (1300)
	 */
	public static int getScreenWidth() {
		return SCREEN_WIDTH;
	}
}
