package com.example.demo.view;

import com.example.demo.menu.PauseMenu;
import javafx.scene.Group;

/**
 * Represents the view layer for a level in the game.
 * This class handles the display of various UI components such as the heart display, win image, and pause menu.
 */
public class LevelView {

	private static final double HEART_DISPLAY_X_POSITION = 5;
	private static final double HEART_DISPLAY_Y_POSITION = 25;
	private static final int WIN_IMAGE_X_POSITION = 355;
	private static final int WIN_IMAGE_Y_POSITION = 175;
	private static final int IMAGE_X_POSITION = 0;
	private static final int IMAGE_Y_POSITION = 0;
	private final Group root;
	private final WinImage winImage;
	private final HeartDisplay heartDisplay;
	private final PauseMenu pauseMenu;

	/**
	 * Constructs a new LevelView with the specified root group and the number of hearts to display.
	 *
	 * @param root The root container for the level's scene.
	 * @param heartsToDisplay The number of hearts to initially display.
	 */
	public LevelView(Group root, int heartsToDisplay) {
		this.root = root;
		this.heartDisplay = new HeartDisplay(HEART_DISPLAY_X_POSITION, HEART_DISPLAY_Y_POSITION, heartsToDisplay);
		this.winImage = new WinImage(WIN_IMAGE_X_POSITION, WIN_IMAGE_Y_POSITION);
		this.pauseMenu = new PauseMenu(IMAGE_X_POSITION, IMAGE_Y_POSITION);
	}

	/**
	 * Displays the heart display UI component in the scene.
	 */
	public void showHeartDisplay() {
		root.getChildren().add(heartDisplay.getContainer());
	}

	/**
	 * Displays the win image when the player wins the level.
	 */
	public void showWinImage() {
		root.getChildren().add(winImage);
		winImage.showWinImage();
	}

	/**
	 * Removes hearts from the heart display to reflect the remaining health.
	 *
	 * @param heartsRemaining The number of hearts remaining after health change.
	 */
	public void removeHearts(int heartsRemaining) {
		int currentNumberOfHearts = heartDisplay.getContainer().getChildren().size();
		for (int i = 0; i < currentNumberOfHearts - heartsRemaining; i++) {
			heartDisplay.removeHeart();
		}
	}

	/**
	 * Toggles the visibility of the pause menu in the scene.
	 * If the pause menu is already displayed, it will be removed and re-added to update its state.
	 */
	public void showPauseMenuImage() {
		if (!root.getChildren().contains(pauseMenu)) {
			root.getChildren().add(pauseMenu);
		} else {
			root.getChildren().remove(pauseMenu);
			root.getChildren().add(pauseMenu);
		}
		pauseMenu.showPauseMenu();
	}

	/**
	 * Hides the pause menu from the scene.
	 */
	public void hidePauseMenuImage() {
		pauseMenu.hidePauseMenu();
	}
}
