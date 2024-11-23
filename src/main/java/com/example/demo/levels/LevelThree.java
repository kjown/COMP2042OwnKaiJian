package com.example.demo.levels;

import com.example.demo.actors.Enemy3;
import com.example.demo.view.LevelViewLevelThree;
import com.example.demo.controller.Controller;
import com.example.demo.view.LevelView;
import javafx.stage.Stage;

/**
 * Represents the third level of the game.
 * In this level, the player faces tougher enemies and must meet a kill target to advance.
 * This class handles level-specific configurations including the background, enemy spawning, and game-over conditions.
 */
public class LevelThree extends LevelParent {

    // Constants
    private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/background3.jpg";
    private static final String NEXT_LEVEL = "com.example.demo.levels.LevelFour";
    private static final int PLAYER_INITIAL_HEALTH = 5;
    private static final int TOTAL_ENEMIES = 5;
    private static final int KILLS_TO_ADVANCE = 5;

    private LevelViewLevelThree levelView;

    /**
     * Constructs the third level of the game with specified screen dimensions.
     *
     * @param screenHeight The height of the game screen.
     * @param screenWidth  The width of the game screen.
     * @param controller   The game controller managing this level.
     * @param stage        The primary stage for rendering.
     */
    public LevelThree(double screenHeight, double screenWidth, Controller controller, Stage stage) {
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
        System.out.println("LevelThree constructor called with height: " + screenHeight + " and width: " + screenWidth);
    }

    /**
     * Checks if the game is over or if the player has reached the kill target to proceed to the next level.
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
     * Spawns enemy units if the total number of enemies is less than the defined limit.
     * Uses the `Enemy3` class to create a more challenging enemy type.
     */
    @Override
    protected void spawnEnemyUnits() {
        if (shouldSpawnNewEnemy()) {
            spawnEnemy();
        }
    }

    /**
     * Determines if a new enemy should be spawned based on the current number of enemies.
     *
     * @return True if a new enemy should be spawned, false otherwise.
     */
    private boolean shouldSpawnNewEnemy() {
        return getCurrentNumberOfEnemies() < TOTAL_ENEMIES;
    }

    /**
     * Spawns a new instance of `Enemy3`.
     */
    private void spawnEnemy() {
        Enemy3 enemy3 = new Enemy3(this.levelView);
        addEnemyUnit(enemy3);
    }

    /**
     * Instantiates the level's view, which includes specific UI components for this level.
     *
     * @return A `LevelView` object configured for this level.
     */
    @Override
    protected LevelView instantiateLevelView() {
        logViewInstantiation();
        this.levelView = new LevelViewLevelThree(getRoot(), PLAYER_INITIAL_HEALTH);
        return this.levelView;
    }

    /**
     * Logs the instantiation of the LevelView for debugging purposes.
     */
    private void logViewInstantiation() {
        System.out.println("Instantiating LevelViewLevelThree");
        System.out.println("LevelViewLevelThree instantiated: " + levelView);
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
