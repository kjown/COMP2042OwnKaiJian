package com.example.demo.view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * Represents a heart display UI component that shows a certain number of hearts.
 * This can be used to visually represent health or lives in the game.
 */
public class HeartDisplay {

	private static final String HEART_IMAGE_NAME = "/com/example/demo/images/heart.png"; // Image file path for the heart icon
	private static final int HEART_HEIGHT = 50; // Height of the heart image
	private static final int INDEX_OF_FIRST_ITEM = 0; // Index to remove the first heart in the display
	private HBox container; // Container to hold the hearts in a horizontal layout
	private double containerXPosition; // X position of the container
	private double containerYPosition; // Y position of the container
	private int numberOfHeartsToDisplay; // Number of hearts to be displayed

	/**
	 * Creates a new HeartDisplay with the specified position and number of hearts to display.
	 *
	 * @param xPosition The initial X position of the heart display container.
	 * @param yPosition The initial Y position of the heart display container.
	 * @param heartsToDisplay The number of hearts to display.
	 */
	public HeartDisplay(double xPosition, double yPosition, int heartsToDisplay) {
		this.containerXPosition = xPosition;
		this.containerYPosition = yPosition;
		this.numberOfHeartsToDisplay = heartsToDisplay;
		initializeContainer();
		initializeHearts();
	}

	/**
	 * Initializes the container (HBox) that will hold the heart images.
	 */
	private void initializeContainer() {
		container = new HBox();
		container.setLayoutX(containerXPosition); // Set the X position of the container
		container.setLayoutY(containerYPosition); // Set the Y position of the container
	}

	/**
	 * Initializes and adds the specified number of heart images to the container.
	 */
	private void initializeHearts() {
		for (int i = 0; i < numberOfHeartsToDisplay; i++) {
			ImageView heart = new ImageView(new Image(getClass().getResource(HEART_IMAGE_NAME).toExternalForm()));

			heart.setFitHeight(HEART_HEIGHT); // Set the height of the heart image
			heart.setPreserveRatio(true); // Maintain the aspect ratio of the heart image
			container.getChildren().add(heart); // Add the heart image to the container
		}
	}

	/**
	 * Removes the first heart from the display. This method is typically used when reducing health.
	 */
	public void removeHeart() {
		if (!container.getChildren().isEmpty()) {
			container.getChildren().remove(INDEX_OF_FIRST_ITEM); // Remove the first heart
		}
	}

	/**
	 * Gets the container that holds the heart images.
	 *
	 * @return the HBox container containing the heart images.
	 */
	public HBox getContainer() {
		return container;
	}
}
