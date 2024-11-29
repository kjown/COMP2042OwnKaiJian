package com.example.demo.levels;

import com.example.demo.actors.ActiveActorDestructible;
import com.example.demo.actors.enemies.Enemy2;
import com.example.demo.controller.Controller;
import com.example.demo.view.LevelView;
import javafx.stage.Stage;

/**
 * Represents the second level of the game.
 * In this level, the player must defeat a set number of enemies to progress.
 * Level-specific configurations include the background image, enemy spawn logic, and player health.
 */
public class LevelTwo extends LevelParent {

    // Constants
    private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/background2.png";
    private static final String NEXT_LEVEL = "com.example.demo.levels.LevelThree";
    private static final int TOTAL_ENEMIES = 5;
    private static final int KILLS_TO_ADVANCE = 5;
    private static final double ENEMY_SPAWN_PROBABILITY = 0.20;
    private static final int PLAYER_INITIAL_HEALTH = 5;

    /**
     * Constructs the second level of the game with specified screen dimensions.
     *
     * @param screenHeight The height of the game screen.
     * @param screenWidth  The width of the game screen.
     * @param controller   The game controller managing this level.
     * @param stage        The primary stage for rendering.
     */
    public LevelTwo(double screenHeight, double screenWidth, Controller controller, Stage stage) {
        super(BACKGROUND_IMAGE_NAME, screenHeight, screenWidth, PLAYER_INITIAL_HEALTH, controller, stage);
        logInitialization(screenHeight, screenWidth);
    }

    /**
     * Logs the initialization parameters of the level.
     *
     * @param screenHeight The height of the game screen.
     * @param screenWidth  The width of the game screen.
     */
    private void logInitialization(double screenHeight, double screenWidth) {
        System.out.println("LevelTwo constructor called with height: " + screenHeight + " and width: " + screenWidth);
    }

    /**
     * Checks if the game is over or if the player has reached the kill target to proceed to the next level.
     * If the player is destroyed, the game ends, otherwise, if the player reaches the kill target,
     * they advance to the next level.
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
     * Initializes friendly units for the level, such as the player character.
     * The player is added to the scene at the start of the level.
     */
    @Override
    protected void initializeFriendlyUnits() {
        addUserToScene();
    }

    /**
     * Adds the user (player) to the scene.
     */
    private void addUserToScene() {
        getRoot().getChildren().add(getUser());
    }

    /**
     * Spawns enemy units at random positions based on the configured spawn probability.
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
     * Calculates how many enemies need to be spawned.
     *
     * @return The number of enemies to spawn.
     */
    private int calculateEnemiesToSpawn() {
        int currentNumberOfEnemies = getCurrentNumberOfEnemies();
        return TOTAL_ENEMIES - currentNumberOfEnemies;
    }

    /**
     * Determines if an enemy should spawn based on a random probability.
     *
     * @return True if an enemy should spawn, false otherwise.
     */
    private boolean shouldSpawnEnemy() {
        return Math.random() < ENEMY_SPAWN_PROBABILITY;
    }

    /**
     * Spawns a new enemy at a random Y position.
     */
    private void spawnEnemy() {
        double newEnemyInitialYPosition = generateRandomEnemyYPosition();
        ActiveActorDestructible newEnemy = new Enemy2(getScreenWidth(), newEnemyInitialYPosition);
        addEnemyUnit(newEnemy);
    }

    /**
     * Generates a random Y position for spawning a new enemy.
     *
     * @return A random Y position within the game's height boundaries.
     */
    private double generateRandomEnemyYPosition() {
        return Math.random() * getEnemyMaximumYPosition();
    }

    /**
     * Instantiates the level's view with the necessary UI components.
     *
     * @return A LevelView object configured for this level.
     */
    @Override
    protected LevelView instantiateLevelView() {
        return new LevelView(getRoot(), PLAYER_INITIAL_HEALTH);
    }

    /**
     * Checks if the user has reached the kill target to advance to the next level.
     *
     * @return True if the player has enough kills to advance, false otherwise.
     */
    private boolean userHasReachedKillTarget() {
        return getUser().getNumberOfKills() >= KILLS_TO_ADVANCE;
    }
}
