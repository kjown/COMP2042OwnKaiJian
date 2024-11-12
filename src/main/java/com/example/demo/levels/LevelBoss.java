package com.example.demo.levels;

import com.example.demo.view.LevelView;
import com.example.demo.view.LevelViewLevelBoss;
import com.example.demo.actors.Boss;
import com.example.demo.controller.Controller;
import javafx.stage.Stage;

public class LevelBoss extends LevelParent {

	private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/background-boss.jpg";
	private static final int PLAYER_INITIAL_HEALTH = 5;
	private Boss boss;
	private LevelViewLevelBoss levelView;

	public LevelBoss(double screenHeight, double screenWidth, Controller controller, Stage stage) {
		super(BACKGROUND_IMAGE_NAME, screenHeight, screenWidth, PLAYER_INITIAL_HEALTH, controller, stage);
		System.out.print("LevelTwo constructor called with height: " + screenHeight + " and width: " + screenWidth);
		boss = new Boss(this.levelView);
		System.out.println("Boss initialised: " + boss);
	}

	@Override
	protected void initializeFriendlyUnits() {
		getRoot().getChildren().add(getUser());
	}

	@Override
	protected void checkIfGameOver() {
		System.out.println("Checking game over conditions...");
		if (userIsDestroyed()) {
			System.out.println("User is destroyed. Losing game.");
			loseGame();
		} else if (boss.isDestroyed()) {
			System.out.println("Boss is destroyed. Winning game.");
			winGame();
		} else {
			System.out.println("Game continues...");
		}
	}


	@Override
	protected void spawnEnemyUnits() {
		if (getCurrentNumberOfEnemies() == 0) {
			boss = new Boss(this.levelView);
			addEnemyUnit(boss);
		}
	}

	@Override
	protected LevelView instantiateLevelView() {
		System.out.println("Instantiating LevelViewLevelTwo");
		this.levelView = new LevelViewLevelBoss(getRoot(), PLAYER_INITIAL_HEALTH);
		System.out.println("LevelViewLevelTwo instantiated: " + levelView);
		return this.levelView;
	}

}
