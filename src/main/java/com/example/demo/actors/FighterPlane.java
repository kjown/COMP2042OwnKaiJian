package com.example.demo.actors;

import com.example.demo.controller.AudioManager;
import javafx.application.Platform;
import javafx.scene.image.Image;

/**
 * The FighterPlane class represents a fighter plane in the game,
 * which is a type of active actor that can take damage and fire projectiles.
 * It extends from ActiveActorDestructible to add health management and projectile firing functionality.
 */
public abstract class FighterPlane extends ActiveActorDestructible {

	private int health;
	private static final String EXPLOSION_IMAGE = "/com/example/demo/images/explode.png";


	/**
	 * Constructs a FighterPlane object with a specified image, position, and health.
	 *
	 * @param imageName     The image file name for the fighter plane's sprite.
	 * @param imageHeight   The height to which the image will be scaled.
	 * @param initialXPos   The initial X position of the fighter plane.
	 * @param initialYPos   The initial Y position of the fighter plane.
	 * @param health        The initial health of the fighter plane.
	 */
	public FighterPlane(String imageName, int imageHeight, double initialXPos, double initialYPos, int health) {
		super(imageName, imageHeight, initialXPos, initialYPos);
		this.health = health;
	}

	/**
	 * Abstract method for firing a projectile from the fighter plane.
	 * This method should return an instance of ActiveActorDestructible representing the projectile.
	 *
	 * @return The fired projectile.
	 */
	public abstract ActiveActorDestructible fireProjectile();

	/**
	 * Decreases the health of the fighter plane by 1 and handles the destruction process when health reaches zero.
	 * Plays the appropriate sound effects for the damage and destruction.
	 */
	@Override
	public void takeDamage() {
		health--;
		// Play hit marker sound
		AudioManager.getInstance().playSoundEffect("/com/example/demo/music/hitmarker.mp3");

		if (healthAtZero()) {
			AudioManager.getInstance().playSoundEffect("/com/example/demo/music/destroyedsound.wav");
			this.destroy();
		}
	}

	/**
	 * Overrides the destroy method to show an explosion before removing the object.
	 */
	@Override
	public void destroy() {
		Platform.runLater(() -> {
			try {
				Image explosionImage = new Image(getClass().getResource("/com/example/demo/images/explode.png").toExternalForm());
				this.setImage(explosionImage);
			} catch (NullPointerException e) {
				System.err.println("Resource not found: /com/example/demo/images/explode.png");
				e.printStackTrace();
			}
		});

		new Thread(() -> {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			Platform.runLater(super::destroy);
		}).start();
	}

	/**
	 * Calculates the X position of the projectile based on the fighter plane's position and a specified offset.
	 *
	 * @param xPositionOffset The horizontal offset from the fighter plane's position.
	 * @return The calculated X position for the projectile.
	 */
	protected double getProjectileXPosition(double xPositionOffset) {
		return getLayoutX() + getTranslateX() + xPositionOffset;
	}

	/**
	 * Calculates the Y position of the projectile based on the fighter plane's position and a specified offset.
	 *
	 * @param yPositionOffset The vertical offset from the fighter plane's position.
	 * @return The calculated Y position for the projectile.
	 */
	protected double getProjectileYPosition(double yPositionOffset) {
		return getLayoutY() + getTranslateY() + yPositionOffset;
	}

	/**
	 * Checks if the health of the fighter plane has reached zero.
	 *
	 * @return true if health is zero, indicating the plane is destroyed, false otherwise.
	 */
	private boolean healthAtZero() {
		return health == 0;
	}

	/**
	 * Gets the current health of the fighter plane.
	 *
	 * @return The current health value.
	 */
	public int getHealth() {
		return health;
	}
}
