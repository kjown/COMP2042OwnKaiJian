package com.example.demo.levels;

import com.example.demo.controller.Controller;
import com.example.demo.view.LevelView;
import com.example.demo.actors.ActiveActorDestructible;
import com.example.demo.actors.enemies.Enemy1;
import javafx.stage.Stage;

/**
 * Represents the first level of the game.
 * This class handles the initialization of the level, spawning enemies,
 * checking game status, and transitioning to the next level.
 * The player must defeat enough enemies to advance to the next level.
 */
public class LevelOne extends LevelParent {

	// Constants
	private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/background1.jpg";
	private static final String NEXT_LEVEL = "com.example.demo.levels.LevelTwo";
	private static final int TOTAL_ENEMIES = 5;
	private static final int KILLS_TO_ADVANCE = 10;
	private static final double ENEMY_SPAWN_PROBABILITY = 0.20;
	private static final int PLAYER_INITIAL_HEALTH = 5;

	/**
	 * Constructor for LevelOne.
	 *
	 * @param screenHeight The height of the game screen.
	 * @param screenWidth The width of the game screen.
	 * @param controller The game controller responsible for managing game states.
	 * @param stage The primary stage of the JavaFX application.
	 */
	public LevelOne(double screenHeight, double screenWidth, Controller controller, Stage stage) {
		super(BACKGROUND_IMAGE_NAME, screenHeight, screenWidth, PLAYER_INITIAL_HEALTH, controller, stage);
		logInitialization(screenHeight, screenWidth);
	}

	/**
	 * Checks if the game is over by evaluating if the player has been destroyed
	 * or if the kill target to advance has been reached.
	 * The game ends if the player is destroyed, or the player advances to the next level if the kill target is reached.
	 */
	@Override
	protected void checkIfGameOver() {
		if (userIsDestroyed()) {
			loseGame();
		} else if (userHasReachedKillTarget()) {
			goToNextLevel(NEXT_LEVEL);
		}
	}

	/**
	 * Initializes friendly units (like the player) at the start of the level.
	 * This method adds the user (player) to the scene.
	 */
	@Override
	protected void initializeFriendlyUnits() {
		addUserToScene();
	}

	/**
	 * Spawns enemy units during the level based on a predefined probability.
	 * This method determines how many enemies should spawn and handles the spawning.
	 */
	@Override
	protected void spawnEnemyUnits() {
		int enemiesToSpawn = calculateEnemiesToSpawn();
		for (int i = 0; i < enemiesToSpawn; i++) {
			if (shouldSpawnEnemy()) {
				spawnEnemy();
			}
		}
	}

	/**
	 * Creates and returns a LevelView object for this level.
	 * This view is used to display the graphical interface of LevelOne.
	 *
	 * @return A LevelView instance for LevelOne.
	 */
	@Override
	protected LevelView instantiateLevelView() {
		return new LevelView(getRoot(), PLAYER_INITIAL_HEALTH);
	}

	/**
	 * Logs the initialization details of the level, including screen dimensions.
	 * This is primarily for debugging and logging purposes.
	 *
	 * @param screenHeight The height of the game screen.
	 * @param screenWidth The width of the game screen.
	 */
	private void logInitialization(double screenHeight, double screenWidth) {
		System.out.println("LevelOne constructor called with height: " + screenHeight + " and width: " + screenWidth);
	}

	/**
	 * Adds the user (player) to the scene at the start of the level.
	 */
	private void addUserToScene() {
		getRoot().getChildren().add(getUser());
	}

	/**
	 * Determines if the player has reached the required kill target to advance to the next level.
	 *
	 * @return true if the player has enough kills to advance, false otherwise.
	 */
	private boolean userHasReachedKillTarget() {
		return getUser().getNumberOfKills() >= KILLS_TO_ADVANCE;
	}

	/**
	 * Calculates the number of enemies that need to be spawned based on the total count.
	 * Ensures that the total number of enemies does not exceed the predefined limit.
	 *
	 * @return The number of enemies to be spawned.
	 */
	private int calculateEnemiesToSpawn() {
		int currentNumberOfEnemies = getCurrentNumberOfEnemies();
		return TOTAL_ENEMIES - currentNumberOfEnemies;
	}

	/**
	 * Randomly determines if an enemy should be spawned based on a predefined probability.
	 * The probability of spawning an enemy is determined by ENEMY_SPAWN_PROBABILITY.
	 *
	 * @return true if an enemy should spawn, false otherwise.
	 */
	private boolean shouldSpawnEnemy() {
		return Math.random() < ENEMY_SPAWN_PROBABILITY;
	}

	/**
	 * Spawns a new enemy of type {@link Enemy1} at a random Y position within the allowable game area.
	 */
	private void spawnEnemy() {
		double newEnemyInitialYPosition = generateRandomEnemyYPosition();
		ActiveActorDestructible newEnemy = new Enemy1(getScreenWidth(), newEnemyInitialYPosition);
		addEnemyUnit(newEnemy);
	}

	/**
	 * Generates a random Y position for a new enemy spawn.
	 * This ensures that enemies are spawned at different vertical positions on the screen.
	 *
	 * @return A random Y position within the allowable bounds for enemy spawning.
	 */
	private double generateRandomEnemyYPosition() {
		return Math.random() * getEnemyMaximumYPosition();
	}
}
