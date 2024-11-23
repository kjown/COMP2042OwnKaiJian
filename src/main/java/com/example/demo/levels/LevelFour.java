package com.example.demo.levels;

import com.example.demo.controller.Controller;
import com.example.demo.view.LevelView;
import com.example.demo.actors.ActiveActorDestructible;
import com.example.demo.actors.Enemy5;
import com.example.demo.actors.Enemy4;

import javafx.stage.Stage;

public class LevelFour extends LevelParent {
    private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/background4.png";
    private static final String NEXT_LEVEL = "com.example.demo.levels.LevelBoss";
    private static final int TOTAL_ENEMIES = 6;
    private static final int KILLS_TO_ADVANCE = TOTAL_ENEMIES;
    private static final int PLAYER_INITIAL_HEALTH = 5;

    private Enemy4 enemy4;  // Reference to the single Enemy4
    private Enemy5 enemy5;

    public LevelFour(double screenHeight, double screenWidth, Controller controller, Stage stage) {
        super(BACKGROUND_IMAGE_NAME, screenHeight, screenWidth, PLAYER_INITIAL_HEALTH, controller, stage);
    }

    @Override
    protected void checkIfGameOver() {
        if (userIsDestroyed()) {
            loseGame();  // Lose the game if the player is destroyed
        }
        else if (isEnemy4Destroyed()) {
            goToNextLevel(NEXT_LEVEL);  // Go to the next level if Enemy4 is destroyed
        }
    }

    /**
     * Checks if Enemy4 has been destroyed.
     *
     * @return true if Enemy4 is destroyed, false otherwise.
     */
    private boolean isEnemy4Destroyed() {
        return enemy4 == null || enemy4.isDestroyed();
    }

    @Override
    protected void initializeFriendlyUnits() {
        getRoot().getChildren().add(getUser());
    }

    protected void spawnEnemyUnits() {
        int currentNumberOfEnemies = getCurrentNumberOfEnemies();

        // Spawn Enemy4
        if (currentNumberOfEnemies == 0) {
            double newEnemy4InitialYPosition = Math.random() * getEnemyMaximumYPosition();
            enemy4 = new Enemy4(getScreenWidth(), newEnemy4InitialYPosition);  // Save the reference to Enemy4
            addEnemyUnit(enemy4);  // Add Enemy4 to the level
        }

        // Spawn Enemy5
        if (currentNumberOfEnemies <  5) {
            double newEnemy1InitialYPosition = Math.random() * getEnemyMaximumYPosition();
            ActiveActorDestructible newEnemy5 = new Enemy5(getScreenWidth(), newEnemy1InitialYPosition);
            addEnemyUnit(newEnemy5);
        }
    }

    @Override
    protected LevelView instantiateLevelView() {
        return new LevelView(getRoot(), PLAYER_INITIAL_HEALTH);
    }

    private boolean userHasReachedKillTarget() {
        return getUser().getNumberOfKills() >= KILLS_TO_ADVANCE;
    }
}
