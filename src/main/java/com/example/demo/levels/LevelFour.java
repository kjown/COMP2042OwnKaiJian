package com.example.demo.levels;

import com.example.demo.controller.Controller;
import com.example.demo.view.LevelView;
import com.example.demo.actors.ActiveActorDestructible;
import com.example.demo.actors.enemies.Enemy5;
import com.example.demo.actors.enemies.Enemy4;
import javafx.stage.Stage;

/**
 * Represents the fourth level of the game.
 * This level introduces two new enemy types: {@link Enemy4} and {@link Enemy5}.
 * The player must defeat {@link Enemy4} to proceed to the next level.
 * The game continues until the player is destroyed or {@link Enemy4} is defeated.
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
     * or if {@link Enemy4} has been defeated.
     * If the player is destroyed, the game is lost. If {@link Enemy4} is defeated, the game proceeds to the next level.
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
     * Checks if {@link Enemy4} has been destroyed.
     *
     * @return true if {@link Enemy4} is destroyed or null, false otherwise.
     */
    private boolean isEnemy4Destroyed() {
        return enemy4 == null || enemy4.isDestroyed();
    }

    /**
     * Initializes friendly units for the level, such as the player character.
     * This method adds the user (player) to the scene.
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
     * Spawns enemy units in the level. This includes a single {@link Enemy4} and multiple {@link Enemy5} instances.
     * The player must defeat {@link Enemy4} to progress.
     */
    @Override
    protected void spawnEnemyUnits() {
        spawnEnemy4IfNotPresent();
        spawnAdditionalEnemies();
    }

    /**
     * Spawns {@link Enemy4} if it is not already present in the level.
     * This method ensures that only one instance of {@link Enemy4} exists in the scene at a time.
     */
    private void spawnEnemy4IfNotPresent() {
        if (getCurrentNumberOfEnemies() == 0) {
            double newEnemy4InitialYPosition = generateRandomYPosition();
            enemy4 = new Enemy4(getScreenWidth(), newEnemy4InitialYPosition);
            addEnemyUnit(enemy4);
        }
    }

    /**
     * Spawns additional enemies ({@link Enemy5}) up to the limit of 5 instances.
     * These enemies are spawned randomly, ensuring a variety of gameplay.
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
     * This value ensures that the enemies are spread across the screen in different vertical positions.
     *
     * @return A double value representing the Y position.
     */
    private double generateRandomYPosition() {
        return Math.random() * getEnemyMaximumYPosition();
    }

    /**
     * Creates and initializes the view for this level.
     * Instantiates a {@link LevelView} object that is specifically configured for this level.
     *
     * @return A {@link LevelView} object configured for this level.
     */
    @Override
    protected LevelView instantiateLevelView() {
        return new LevelView(getRoot(), PLAYER_INITIAL_HEALTH);
    }



    public boolean isGameOver() {
        return userIsDestroyed() || isEnemy4Destroyed();
    }

    public Enemy4 getEnemy4() {
        return enemy4;
    }
}
