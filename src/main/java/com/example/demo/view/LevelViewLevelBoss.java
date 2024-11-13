// LevelViewLevelTwo.java
package com.example.demo.view;

import javafx.scene.Group;

public class LevelViewLevelBoss extends LevelView {

	private static final int SHIELD_X_POSITION = 50;
	private static final int SHIELD_Y_POSITION = 50;
	private final Group root;
	private final ShieldImage shieldImage;

	public LevelViewLevelBoss(Group root, int heartsToDisplay) {
		super(root, heartsToDisplay);
		this.root = root;
		this.shieldImage = new ShieldImage(SHIELD_X_POSITION, SHIELD_Y_POSITION, 50); // Example initial health
	}

	public Group getRoot() {
		return root;
	}

	public void showShield() {
		if (!root.getChildren().contains(shieldImage)) {
			root.getChildren().add(shieldImage);
		}
		shieldImage.showShield();
	}

	public void hideShield() {
		shieldImage.hideShield();
	}
}