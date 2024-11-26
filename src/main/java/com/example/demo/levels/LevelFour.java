package com.example.demo.levels;

import com.example.demo.controller.Controller;
import com.example.demo.view.LevelView;
import com.example.demo.actors.ActiveActorDestructible;
import com.example.demo.actors.enemies.Enemy5;
import com.example.demo.actors.enemies.Enemy4;
import javafx.stage.Stage;

/**
 * Represents the fourth level of the game.
 * This level introduces two new enemy types: `Enemy4` and `Enemy5`.
 * The player must defeat `Enemy4` to proceed to the next level.
 */
public class LevelFour extends LevelParent {

    // Constants
    private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/background4.png";
    private static final String NEXT_LEVEL = "com.example.demo.levels.LevelBoss";
    private static final int TOTAL_ENEMIES = 6;
    private static final int KILLS_TO_ADVANCE = TOTAL_ENEMIES;
    private static final int PLAYER_INITIAL_HEALTH = 5;

    private Enemy4 enemy4; // Reference to the primary target Enemy4

    /**
     * Constructs the fourth level of the game with specified screen dimensions.
     *
     * @param screenHeight The height of the game screen.
     * @param screenWidth  The width of the game screen.
     * @param controller   The game controller managing this level.
     * @param stage        The primary stage for rendering.
     */
    public LevelFour(double screenHeight, double screenWidth, Controller controller, Stage stage) {
        super(BACKGROUND_IMAGE_NAME, screenHeight, screenWidth, PLAYER_INITIAL_HEALTH, controller, stage);
    }

    /**
     * Checks if the game is over. The game is considered over if the player is destroyed
     * or if `Enemy4` has been defeated.
     */
    @Override
    protected void checkIfGameOver() {
        if (userIsDestroyed()) {
            loseGame(); // Ends the game if the player is destroyed
        } else if (isEnemy4Destroyed()) {
            goToNextLevel(NEXT_LEVEL); // Proceeds to the next level if Enemy4 is defeated
        }
    }

    /**
     * Checks if `Enemy4` has been destroyed.
     *
     * @return true if `Enemy4` is destroyed or null, false otherwise.
     */
    private boolean isEnemy4Destroyed() {
        return enemy4 == null || enemy4.isDestroyed();
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
     * Spawns enemy units in the level. This includes a single `Enemy4` and multiple `Enemy5` instances.
     * The player must defeat `Enemy4` to progress.
     */
    @Override
    protected void spawnEnemyUnits() {
        spawnEnemy4IfNotPresent();
        spawnAdditionalEnemies();
    }

    /**
     * Spawns `Enemy4` if it is not already present in the level.
     */
    private void spawnEnemy4IfNotPresent() {
        if (getCurrentNumberOfEnemies() == 0) {
            double newEnemy4InitialYPosition = generateRandomYPosition();
            enemy4 = new Enemy4(getScreenWidth(), newEnemy4InitialYPosition);
            addEnemyUnit(enemy4);
        }
    }

    /**
     * Spawns additional enemies (`Enemy5`) up to the limit of 5 instances.
     */
    private void spawnAdditionalEnemies() {
        if (getCurrentNumberOfEnemies() < TOTAL_ENEMIES - 1) {
            double newEnemy5InitialYPosition = generateRandomYPosition();
            ActiveActorDestructible newEnemy5 = new Enemy5(getScreenWidth(), newEnemy5InitialYPosition);
            addEnemyUnit(newEnemy5);
        }
    }

    /**
     * Generates a random Y position for enemy spawning.
     *
     * @return A double value representing the Y position.
     */
    private double generateRandomYPosition() {
        return Math.random() * getEnemyMaximumYPosition();
    }

    /**
     * Creates and initializes the view for this level.
     *
     * @return A `LevelView` object configured for this level.
     */
    @Override
    protected LevelView instantiateLevelView() {
        return new LevelView(getRoot(), PLAYER_INITIAL_HEALTH);
    }
}
