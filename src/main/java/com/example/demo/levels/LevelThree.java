package com.example.demo.levels;

import com.example.demo.actors.Enemy3;
import com.example.demo.view.LevelViewLevelThree;
import com.example.demo.controller.Controller;
import com.example.demo.view.LevelView;
import javafx.stage.Stage;

public class Level3 extends LevelParent {
    private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/background3.jpg";
    private static final String NEXT_LEVEL = "com.example.demo.levels.LevelBoss";
    private static final int PLAYER_INITIAL_HEALTH = 5;
    private static final int TOTAL_ENEMIES = 2;
    private static final int KILLS_TO_ADVANCE = 5;

    private LevelViewLevelThree levelView;
    private Enemy3 enemy3;

    public Level3(double screenHeight, double screenWidth, Controller controller, Stage stage) {
        super(BACKGROUND_IMAGE_NAME, screenHeight, screenWidth, PLAYER_INITIAL_HEALTH, controller, stage);
        System.out.println("LevelThree constructor called with height: " + screenHeight + " and width: " + screenWidth);
        System.out.println("Enemy3 initialised: " + enemy3);
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
    protected void spawnEnemyUnits() {
        if (getCurrentNumberOfEnemies() < TOTAL_ENEMIES) {
            // Spawn a new instance of Enemy3 if there are less than 2 on the field
            Enemy3 enemy3 = new Enemy3(this.levelView);
            addEnemyUnit(enemy3);
        }
    }

    @Override
    protected LevelView instantiateLevelView() {
        System.out.println("Instantiating LevelViewLevelThree");
        this.levelView = new LevelViewLevelThree(getRoot(), PLAYER_INITIAL_HEALTH);
        System.out.println("LevelViewLevelThree instantiated: " + levelView);
        return this.levelView;
    }
    protected void initializeFriendlyUnits() {
        getRoot().getChildren().add(getUser());
    }

    private boolean userHasReachedKillTarget() {
        return getUser().getNumberOfKills() >= KILLS_TO_ADVANCE;
    }
}
