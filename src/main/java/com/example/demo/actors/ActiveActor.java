package com.example.demo.actors;

import javafx.scene.image.*;

/**
 * The ActiveActor class represents an actor in the game that is an active object with a visual image.
 * It extends the ImageView class to display an image and provides functionality to update its position.
 * Concrete subclasses should implement the {@link #updatePosition()} method to define how the actor's position is updated each frame.
 */
public abstract class ActiveActor extends ImageView {

	// The path location for the images used by actors
	private static final String IMAGE_LOCATION = "/com/example/demo/images/";

	/**
	 * Constructor for creating an ActiveActor with the specified image, size, and initial position.
	 *
	 * @param imageName The name of the image file to display for the actor.
	 * @param imageHeight The height to which the image should be scaled.
	 * @param initialXPos The initial horizontal position (X coordinate) of the actor.
	 * @param initialYPos The initial vertical position (Y coordinate) of the actor.
	 */
	public ActiveActor(String imageName, int imageHeight, double initialXPos, double initialYPos) {
		// Set the image for the actor using the image name provided
		this.setImage(new Image(getClass().getResource(IMAGE_LOCATION + imageName).toExternalForm()));

		// Set the initial position of the actor
		this.setLayoutX(initialXPos);
		this.setLayoutY(initialYPos);

		// Set the height of the image and preserve the aspect ratio
		this.setFitHeight(imageHeight);
		this.setPreserveRatio(true);
	}

	/**
	 * Abstract method that must be implemented by subclasses to define how the actor's position is updated.
	 * This method is expected to update the actor's position each frame of the game or animation.
	 */
	public abstract void updatePosition();

	/**
	 * Moves the actor horizontally by the specified distance.
	 * This method adjusts the actor's current horizontal position by the given amount.
	 *
	 * @param horizontalMove The distance to move the actor along the X axis.
	 */
	protected void moveHorizontally(double horizontalMove) {
		this.setTranslateX(getTranslateX() + horizontalMove);
	}

	/**
	 * Moves the actor vertically by the specified distance.
	 * This method adjusts the actor's current vertical position by the given amount.
	 *
	 * @param verticalMove The distance to move the actor along the Y axis.
	 */
	protected void moveVertically(double verticalMove) {
		this.setTranslateY(getTranslateY() + verticalMove);
	}
}
