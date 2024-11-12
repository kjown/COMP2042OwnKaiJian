package com.example.demo.levels;

import com.example.demo.actors.ActiveActorDestructible;
import com.example.demo.actors.Enemy1;
import com.example.demo.controller.Controller;
import com.example.demo.view.LevelView;
import javafx.stage.Stage;



public class LevelTwo extends LevelParent {
    private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/background1.jpg";
    private static final String NEXT_LEVEL = "com.example.demo.levels.LevelBoss";
    private static final int TOTAL_ENEMIES = 10;
    private static final int KILLS_TO_ADVANCE = 20;
    private static final double ENEMY_SPAWN_PROBABILITY = .50;
    private static final int PLAYER_INITIAL_HEALTH = 5;

    public LevelTwo(double screenHeight, double screenWidth, Controller controller, Stage stage) {
        super(BACKGROUND_IMAGE_NAME, screenHeight, screenWidth, 5, controller, stage);
        System.out.println("LevelTwo constructor called with height: " + screenHeight + " and width: " + screenWidth);
    }

    @Override
    protected void checkIfGameOver() {
        if (userIsDestroyed()) {
            loseGame();
        }
        else if (userHasReachedKillTarget())
            goToNextLevel(NEXT_LEVEL);
    }
    @Override
    protected void initializeFriendlyUnits() {
        getRoot().getChildren().add(getUser());
    }

    @Override
    protected void spawnEnemyUnits() {
        int currentNumberOfEnemies = getCurrentNumberOfEnemies();
        for (int i = 0; i < TOTAL_ENEMIES - currentNumberOfEnemies; i++) {
            if (Math.random() < ENEMY_SPAWN_PROBABILITY) {
                double newEnemyInitialYPosition = Math.random() * getEnemyMaximumYPosition();
                ActiveActorDestructible newEnemy = new Enemy1(getScreenWidth(), newEnemyInitialYPosition);
                addEnemyUnit(newEnemy);
            }
        }
    }

    protected LevelView instantiateLevelView() {
        return new LevelView(getRoot(), PLAYER_INITIAL_HEALTH);
    }

    private boolean userHasReachedKillTarget() {
        return getUser().getNumberOfKills() >= KILLS_TO_ADVANCE;
    }
}

