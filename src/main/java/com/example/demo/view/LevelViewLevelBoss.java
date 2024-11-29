package com.example.demo.view;

import javafx.scene.Group;

/**
 * Represents the view for the boss level in the game.
 * This class extends {@link LevelView} and adds additional functionality for displaying and hiding the shield UI component.
 */
public class LevelViewLevelBoss extends LevelView {

	private static final int SHIELD_X_POSITION = 50;
	private static final int SHIELD_Y_POSITION = 50;
	private final Group root;
	private final ShieldImage shieldImage;

	/**
	 * Constructs a new LevelViewLevelBoss with the specified root group and the number of hearts to display.
	 *
	 * @param root The root container for the level's scene.
	 * @param heartsToDisplay The number of hearts to initially display.
	 */
	public LevelViewLevelBoss(Group root, int heartsToDisplay) {
		super(root, heartsToDisplay);
		this.root = root;
		this.shieldImage = new ShieldImage(SHIELD_X_POSITION, SHIELD_Y_POSITION, 50); // Example initial health
	}

	/**
	 * Returns the root container for the boss level.
	 *
	 * @return The root {@link Group} for the level.
	 */
	public Group getRoot() {
		return root;
	}

	/**
	 * Displays the shield image in the scene.
	 * If the shield is already displayed, it will not be added again.
	 */
	public void showShield() {
		if (!root.getChildren().contains(shieldImage)) {
			root.getChildren().add(shieldImage);
		}
		shieldImage.showShield();
	}

	/**
	 * Hides the shield image from the scene.
	 */
	public void hideShield() {
		shieldImage.hideShield();
	}
}
