package com.example.demo.view;

import com.example.demo.controller.AudioManager;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class WinImage extends ImageView {

	private static final String IMAGE_NAME = "/com/example/demo/images/youwin.png";
	private static final int HEIGHT = 500;
	private static final int WIDTH = 600;
	private static final String WIN_SOUND_PATH = "/com/example/demo/sounds/winsound.wav";  // Path to your win sound

	private final AudioManager audioManager;

	public WinImage(double xPosition, double yPosition) {
		this.setImage(new Image(getClass().getResource(IMAGE_NAME).toExternalForm()));
		this.setVisible(false);
		this.setFitHeight(HEIGHT);
		this.setFitWidth(WIDTH);
		this.setLayoutX(xPosition);
		this.setLayoutY(yPosition);

		// Initialize the AudioManager instance
		audioManager = AudioManager.getInstance();
	}

	public void showWinImage() {
		this.setVisible(true);

		// Play the win sound when the image is shown
		audioManager.playSoundEffect(WIN_SOUND_PATH);
	}
}