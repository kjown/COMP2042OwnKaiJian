package com.example.demo.view;

import com.example.demo.menu.PauseMenu;
import javafx.scene.Group;

public class LevelView {
	
	private static final double HEART_DISPLAY_X_POSITION = 5;
	private static final double HEART_DISPLAY_Y_POSITION = 25;
	private static final int WIN_IMAGE_X_POSITION = 355;
	private static final int WIN_IMAGE_Y_POSITION = 175;
	private static final int LOSS_SCREEN_X_POSITION = -160;
	private static final int LOSS_SCREEN_Y_POSISITION = -375;
	private static final int IMAGE_X_POSITION = 0;
	private static final int IMAGE_Y_POSITION = 0;
	private static final int SCREEN_WIDTH = 1300;
	private static final int SCREEN_HEIGHT = 750;
	private final Group root;
	private final WinImage winImage;
//	private final GameOverImage gameOverImage;
	private final HeartDisplay heartDisplay;
	private final PauseMenu pauseMenu;

	public LevelView(Group root, int heartsToDisplay) {
		this.root = root;
		this.heartDisplay = new HeartDisplay(HEART_DISPLAY_X_POSITION, HEART_DISPLAY_Y_POSITION, heartsToDisplay);
		this.winImage = new WinImage(WIN_IMAGE_X_POSITION, WIN_IMAGE_Y_POSITION);
//		this.gameOverImage = new GameOverImage(LOSS_SCREEN_X_POSITION, LOSS_SCREEN_Y_POSISITION);
		this.pauseMenu = new PauseMenu(IMAGE_X_POSITION, IMAGE_Y_POSITION);
	}
	
	public void showHeartDisplay() {
		root.getChildren().add(heartDisplay.getContainer());
	}

	public void showWinImage() {
		root.getChildren().add(winImage);
		winImage.showWinImage();
	}

	
	public void removeHearts(int heartsRemaining) {
		int currentNumberOfHearts = heartDisplay.getContainer().getChildren().size();
		for (int i = 0; i < currentNumberOfHearts - heartsRemaining; i++) {
			heartDisplay.removeHeart();
		}
	}
	public void showPauseMenuImage() {
		if (!root.getChildren().contains(pauseMenu)) {
			root.getChildren().add(pauseMenu);
		} else {
			root.getChildren().remove(pauseMenu);
			root.getChildren().add(pauseMenu);
		}
		pauseMenu.showPauseMenu();
	}
	public  void hidePauseMenuImage() {
		pauseMenu.hidePauseMenu();
	}
}
