package com.example.demo.view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Represents a "You Win" image that is displayed when the player wins the game.
 * This class manages the visibility and positioning of the win image on the screen.
 */
public class WinImage extends ImageView {

	private static final String IMAGE_NAME = "/com/example/demo/images/youwin.png";
	private static final int HEIGHT = 500;
	private static final int WIDTH = 600;

	/**
	 * Constructs a new WinImage at the specified position.
	 * The image is initially hidden and can be shown when the player wins.
	 *
	 * @param xPosition The X coordinate for the position of the win image.
	 * @param yPosition The Y coordinate for the position of the win image.
	 */
	public WinImage(double xPosition, double yPosition) {
		this.setImage(new Image(getClass().getResource(IMAGE_NAME).toExternalForm()));
		this.setVisible(false);  // The win image is hidden by default
		this.setFitHeight(HEIGHT);
		this.setFitWidth(WIDTH);
		this.setLayoutX(xPosition);
		this.setLayoutY(yPosition);
	}

	/**
	 * Makes the win image visible on the screen to indicate the player has won.
	 */
	public void showWinImage() {
		this.setVisible(true);
	}
}
