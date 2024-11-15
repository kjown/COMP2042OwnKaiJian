package com.example.demo.levels;

import com.example.demo.controller.Controller;
import com.example.demo.view.LevelView;
import com.example.demo.actors.ActiveActorDestructible;
import com.example.demo.actors.Enemy1;
import javafx.stage.Stage;

public class LevelOne extends LevelParent {

	// Constants
	private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/background1.jpg";
	private static final String NEXT_LEVEL = "com.example.demo.levels.LevelTwo";
	private static final int TOTAL_ENEMIES = 5;
	private static final int KILLS_TO_ADVANCE = 10;
	private static final double ENEMY_SPAWN_PROBABILITY = 0.20;
	private static final int PLAYER_INITIAL_HEALTH = 5;

	// Constructor
	public LevelOne(double screenHeight, double screenWidth, Controller controller, Stage stage) {
		super(BACKGROUND_IMAGE_NAME, screenHeight, screenWidth, PLAYER_INITIAL_HEALTH, controller, stage);
		logInitialization(screenHeight, screenWidth);
	}

	@Override
	protected void checkIfGameOver() {
		if (userIsDestroyed()) {
			loseGame();
		} else if (userHasReachedKillTarget()) {
			goToNextLevel(NEXT_LEVEL);
		}
	}

	@Override
	protected void initializeFriendlyUnits() {
		addUserToScene();
	}

	@Override
	protected void spawnEnemyUnits() {
		int enemiesToSpawn = calculateEnemiesToSpawn();
		for (int i = 0; i < enemiesToSpawn; i++) {
			if (shouldSpawnEnemy()) {
				spawnEnemy();
			}
		}
	}

	@Override
	protected LevelView instantiateLevelView() {
		return new LevelView(getRoot(), PLAYER_INITIAL_HEALTH);
	}

	// Helper method to log initialization
	private void logInitialization(double screenHeight, double screenWidth) {
		System.out.println("LevelOne constructor called with height: " + screenHeight + " and width: " + screenWidth);
	}

	// Adds the user (player) to the scene
	private void addUserToScene() {
		getRoot().getChildren().add(getUser());
	}

	// Determines if the user has reached the kill target to advance the level
	private boolean userHasReachedKillTarget() {
		return getUser().getNumberOfKills() >= KILLS_TO_ADVANCE;
	}

	// Calculates how many enemies need to be spawned
	private int calculateEnemiesToSpawn() {
		int currentNumberOfEnemies = getCurrentNumberOfEnemies();
		return TOTAL_ENEMIES - currentNumberOfEnemies;
	}

	// Randomly decides if an enemy should spawn based on the probability
	private boolean shouldSpawnEnemy() {
		return Math.random() < ENEMY_SPAWN_PROBABILITY;
	}

	// Spawns a new enemy at a random Y position
	private void spawnEnemy() {
		double newEnemyInitialYPosition = generateRandomEnemyYPosition();
		ActiveActorDestructible newEnemy = new Enemy1(getScreenWidth(), newEnemyInitialYPosition);
		addEnemyUnit(newEnemy);
	}

	// Generates a random Y position for a new enemy
	private double generateRandomEnemyYPosition() {
		return Math.random() * getEnemyMaximumYPosition();
	}
}
