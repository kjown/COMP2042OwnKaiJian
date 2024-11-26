package com.example.demo.levels;

import com.example.demo.view.LevelView;
import com.example.demo.view.LevelViewLevelBoss;
import com.example.demo.actors.enemies.Boss;
import com.example.demo.controller.Controller;
import javafx.stage.Stage;

/**
 * Represents the final Boss level of the game.
 * This level pits the player against a single boss enemy. Defeating the boss completes the game.
 */
public class LevelBoss extends LevelParent {

	// Constants
	private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/background-boss.jpg";
	private static final int PLAYER_INITIAL_HEALTH = 5;

	private Boss boss;  // The main boss enemy for this level
	private LevelViewLevelBoss levelView;  // Specialized view for the boss level

	/**
	 * Constructs the final boss level with specified screen dimensions.
	 *
	 * @param screenHeight The height of the game screen.
	 * @param screenWidth  The width of the game screen.
	 * @param controller   The game controller managing this level.
	 * @param stage        The primary stage for rendering.
	 */
	public LevelBoss(double screenHeight, double screenWidth, Controller controller, Stage stage) {
		super(BACKGROUND_IMAGE_NAME, screenHeight, screenWidth, PLAYER_INITIAL_HEALTH, controller, stage);
		initializeBoss();
	}

	/**
	 * Initializes the boss entity and logs the initialization.
	 */
	private void initializeBoss() {
		this.boss = new Boss(this.levelView);
		System.out.println("Boss initialized: " + boss);
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
	 * Checks if the game is over. The game ends when the player is destroyed or the boss is defeated.
	 */
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

	/**
	 * Spawns the boss enemy if it hasn't already been added to the scene.
	 */
	@Override
	protected void spawnEnemyUnits() {
		if (getCurrentNumberOfEnemies() == 0) {
			spawnBoss();
		}
	}

	/**
	 * Creates and adds the boss to the game scene.
	 */
	private void spawnBoss() {
		boss = new Boss(this.levelView);
		addEnemyUnit(boss);
		System.out.println("Boss spawned: " + boss);
	}

	/**
	 * Creates and initializes the view for this level.
	 *
	 * @return A `LevelViewLevelBoss` object configured for this level.
	 */
	@Override
	protected LevelView instantiateLevelView() {
		System.out.println("Instantiating LevelViewLevelBoss");
		this.levelView = new LevelViewLevelBoss(getRoot(), PLAYER_INITIAL_HEALTH);
		System.out.println("LevelViewLevelBoss instantiated: " + levelView);
		return this.levelView;
	}
}
