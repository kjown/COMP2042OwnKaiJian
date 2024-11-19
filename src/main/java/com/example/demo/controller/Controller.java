package com.example.demo.controller;

import com.example.demo.controller.AudioManager;
import com.example.demo.menu.StartMenu;
import com.example.demo.levels.LevelParent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.lang.reflect.Constructor;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import java.util.Observable;
import java.util.Observer;

public class Controller implements Observer{

	public static final String LEVEL_ONE_CLASS_NAME = "com.example.demo.levels.LevelOne";
	private final Stage stage;
	private LevelParent currentLevel;
	private boolean isLoadingLevel = false;
	private final int screenWidth;
	private final int screenHeight;

	private final AudioManager audioManager;

	/**
	 * Constructor for the Controller class with the specified stage, screen width and screen height
	 *
	 * @param stage       the primary stage for this application, onto which
	 *                   the application scene can be set.
	 * @param screenWidth the width of the screen
	 * @param screenHeight the height of the screen
	 */
	public Controller(Stage stage, int screenWidth, int screenHeight) {
		this.stage = stage;
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;

		this.audioManager = new AudioManager();  // Initialize the AudioManager
		System.out.println("Controller initialized");
	}

	/**
	 * Launch the game by displaying the primary stage and loading the first level
	 */
	public void launchGame() {
		System.out.println("Launching game...");
		try {
			// Initialize background music when starting the game
			audioManager.initializeBackgroundMusic("/com/example/demo/music/backgroundmusic.mp3");
			stage.show();
			goToLevel(LEVEL_ONE_CLASS_NAME);
		} catch (Exception e) {
			System.out.println("Exception caught in launchGame:");
			e.printStackTrace();
			showAlert("Error launching game", e);
		}
	}

	/**
	 * Load and display the level with the specified class name
	 *
	 * @param className the class name of the level to load
	 */
	public void goToLevel(String className) {
		if (isLoadingLevel) {
			System.out.println("Already loading a level, skipping: " + className);
			return;
		}
		isLoadingLevel = true;

		try {
			System.out.println("Going to level: " + className);

			// Stop and remove observer from the current level if it exists
			if (currentLevel != null) {
				currentLevel.stopGame();
				currentLevel.deleteObserver(this);
			}

			// Load and instantiate the new level
			Class<?> levelClass = Class.forName(className);
			Constructor<?> constructor = levelClass.getConstructor(double.class, double.class, Controller.class, Stage.class);
			LevelParent newLevel = (LevelParent) constructor.newInstance(stage.getHeight(), stage.getWidth(), this, stage);

			// Set up the new level
			newLevel.addObserver(this);
			Scene scene = newLevel.initializeScene();
			stage.setScene(scene);
			newLevel.startGame();

			// Update the current level reference
			currentLevel = newLevel;
		} catch (Exception e) {
			System.out.println("Exception caught in goToLevel:");
			e.printStackTrace();
			showAlert("Error loading level: " + className, e);
		} finally {
			isLoadingLevel = false;
		}
	}

	// Getter and Setter for the sound state
	public boolean isBackgroundMusicOn() {
		return audioManager.isBackgroundMusicOn();
	}

	public void setBackgroundMusicOn(boolean isOn) {
		audioManager.setBackgroundMusicOn(isOn);
	}

	public void pauseBackgroundMusic() {
		audioManager.pauseBackgroundMusic();
	}

	public void resumeBackgroundMusic() {
		audioManager.resumeBackgroundMusic();
	}

	/**
	 * Updates the observer with the specified observable object and argument
	 *
	 * @param observable the observable object.
	 * @param arg        an argument passed to the {@code notifyObservers} method.
	 */
	public void update(Observable observable, Object arg) {
		if (arg instanceof String nextLevelClassName) {
			goToLevel(nextLevelClassName);
		} else {
			System.out.println("Unknown level transition requested.");
		}
	}

	/**
	 * Display an alert with the specified message and exception details
	 *
	 * @param message the message to display in the alert
	 * @param e       the exception to display in the alert
	 */
	private void showAlert(String message, Exception e) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error");
		alert.setHeaderText(message);
		alert.setContentText(e.toString());
		alert.showAndWait();
	}

	/**
	 * Transitions to the main menu.
	 */
	public void goToMainMenu() {
		StartMenu startMenu = new StartMenu(stage, screenWidth, screenHeight, this);
		startMenu.show();
	}
}