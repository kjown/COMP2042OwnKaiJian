package com.example.demo.actors;

/**
 * The ActiveActorDestructible class represents an actor in the game that can be destroyed.
 * It extends the {@link ActiveActor} class and implements the {@link Destructible} interface.
 * This class provides additional functionality for handling destruction, damage, and updates to the actor.
 * Concrete subclasses should implement the {@link #updatePosition()} and {@link #updateActor()} methods to define how the actor behaves during the game.
 */
public abstract class ActiveActorDestructible extends ActiveActor implements Destructible {

	// Flag indicating whether the actor has been destroyed
	private boolean isDestroyed;

	/**
	 * Constructor for creating an ActiveActorDestructible with the specified image, size, and initial position.
	 * Additionally, the actor is initialized as not destroyed.
	 *
	 * @param imageName The name of the image file to display for the actor.
	 * @param imageHeight The height to which the image should be scaled.
	 * @param initialXPos The initial horizontal position (X coordinate) of the actor.
	 * @param initialYPos The initial vertical position (Y coordinate) of the actor.
	 */
	public ActiveActorDestructible(String imageName, int imageHeight, double initialXPos, double initialYPos) {
		super(imageName, imageHeight, initialXPos, initialYPos);
		isDestroyed = false; // Initially, the actor is not destroyed
	}

	/**
	 * Abstract method that must be implemented by subclasses to define how the actor's position is updated.
	 * This method is expected to update the actor's position each frame of the game or animation.
	 */
	@Override
	public abstract void updatePosition();

	/**
	 * Abstract method that must be implemented by subclasses to define how the actor is updated.
	 * This could include logic such as animation, checking for destruction, or other behavior updates.
	 */
	public abstract void updateActor();

	/**
	 * Method to handle damage taken by the actor.
	 * This method should define how the actor responds to taking damage, such as reducing health or triggering a destruction state.
	 */
	@Override
	public abstract void takeDamage();

	/**
	 * Destroys the actor by setting the destruction flag to true.
	 * This can be used to mark the actor as destroyed, preventing further actions or movement.
	 */
	@Override
	public void destroy() {
		setDestroyed(true); // Set the destruction flag
	}

	/**
	 * Sets the destroyed flag to indicate whether the actor is destroyed.
	 *
	 * @param isDestroyed The destruction state to set for the actor.
	 */
	protected void setDestroyed(boolean isDestroyed) {
		this.isDestroyed = isDestroyed;
	}

	/**
	 * Checks whether the actor is destroyed.
	 *
	 * @return true if the actor is destroyed, false otherwise.
	 */
	public boolean isDestroyed() {
		return isDestroyed;
	}
}
