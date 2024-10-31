package com.example.demo.levels;

import com.example.demo.view.LevelView;
import com.example.demo.view.LevelViewLevelTwo;
import com.example.demo.actors.Boss;

public class LevelTwo extends LevelParent {

	private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/background2.jpg";
	private static final int PLAYER_INITIAL_HEALTH = 5;
	private final Boss boss;
	private LevelViewLevelTwo levelView;

	public LevelTwo(double screenHeight, double screenWidth) {
		super(BACKGROUND_IMAGE_NAME, screenHeight, screenWidth, PLAYER_INITIAL_HEALTH);
		System.out.print("LevelTwo constructor called with height: " + screenHeight + " and width: " + screenWidth);
		boss = new Boss();
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
			addEnemyUnit(boss);
		}
	}

	@Override
	protected LevelView instantiateLevelView() {
		System.out.println("Instantiating LevelViewLevelTwo");
		levelView = new LevelViewLevelTwo(getRoot(), PLAYER_INITIAL_HEALTH);
		System.out.println("LevelViewLevelTwo instantiated: " + levelView);
		return levelView;
	}

}
