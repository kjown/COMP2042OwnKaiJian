package com.example.demo.controller;

import java.lang.reflect.Constructor;
import java.util.Observable;
import java.util.Observer;

import com.example.demo.StartMenu;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import com.example.demo.levels.LevelParent;

public class Controller implements Observer {

	public static final String LEVEL_ONE_CLASS_NAME = "com.example.demo.levels.LevelOne";
	private final Stage stage;
	private LevelParent currentLevel;
	private boolean isLoadingLevel = false;
	private final int screenWidth;
	private final int screenHeight;

	public Controller(Stage stage, int screenWidth, int screenHeight) {
		this.stage = stage;
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;

		System.out.println("Controller initialized");
	}

	public void launchGame() {
		System.out.println("Launching game...");
		try {
			stage.show();
			goToLevel(LEVEL_ONE_CLASS_NAME);
		} catch (Exception e) {
			System.out.println("Exception caught in launchGame:");
			e.printStackTrace();
			showAlert("Error launching game", e);
		}
	}

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

	@Override
	public void update(Observable observable, Object arg) {
		if (arg instanceof String nextLevelClassName) {
			goToLevel(nextLevelClassName);
		} else {
			System.out.println("Unknown level transition requested.");
		}
	}

	private void showAlert(String message, Exception e) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error");
		alert.setHeaderText(message);
		alert.setContentText(e.toString());
		alert.showAndWait();
	}

	public void goToMainMenu() {
		StartMenu startMenu = new StartMenu(stage, screenWidth, screenHeight, this);
		startMenu.show();
	}
}
