package com.example.demo.levels;

import com.example.demo.controller.Controller;
import com.example.demo.view.LevelView;
import com.example.demo.actors.ActiveActorDestructible;
import com.example.demo.actors.Enemy1;
import com.example.demo.actors.Enemy4;

import javafx.stage.Stage;

public class LevelFour extends LevelParent {
    private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/background4.jpg";
    private static final String NEXT_LEVEL = "com.example.demo.levels.LevelBoss";
    private static final int TOTAL_ENEMIES = 6;
    private static final int KILLS_TO_ADVANCE = TOTAL_ENEMIES;
    private static final int PLAYER_INITIAL_HEALTH = 5;

    public LevelFour(double screenHeight, double screenWidth, Controller controller, Stage stage) {
        super(BACKGROUND_IMAGE_NAME, screenHeight, screenWidth, PLAYER_INITIAL_HEALTH, controller, stage);
    }

    @Override
    protected void checkIfGameOver() {
        if (userIsDestroyed()) {
            loseGame();
        }
        else if (userHasReachedKillTarget()) {
            goToNextLevel(NEXT_LEVEL);
        }
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
            ActiveActorDestructible newEnemy4 = new Enemy4(getScreenWidth(), newEnemy4InitialYPosition);
            addEnemyUnit(newEnemy4);
        }

        // Spawn Enemy1
        if (currentNumberOfEnemies <  5) {
            double newEnemy1InitialYPosition = Math.random() * getEnemyMaximumYPosition();
            ActiveActorDestructible newEnemy1 = new Enemy1(getScreenWidth(), newEnemy1InitialYPosition);
            addEnemyUnit(newEnemy1);
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
