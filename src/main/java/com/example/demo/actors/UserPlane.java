package com.example.demo.actors;

import com.example.demo.controller.AudioManager;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class UserPlane extends FighterPlane {

	private static final String IMAGE_NAME = "userplane.png";
	private static final double Y_UPPER_BOUND = -10;
	private static final double Y_LOWER_BOUND = 675.0;
	private static final double INITIAL_X_POSITION = 5.0;
	private static final double INITIAL_Y_POSITION = 300.0;
	private static final int IMAGE_HEIGHT = 40;
	private static final int VERTICAL_VELOCITY = 8;
	private static final int HORIZONTAL_VELOCITY = 8;
	private static final int PROJECTILE_X_POSITION = 110;
	private static final int PROJECTILE_Y_POSITION_OFFSET = 20;
	private static final String SHOOTING_SFX_PATH = "/com/example/demo/music/shootsound.wav";

	private int verticalVelocityMultiplier;
	private int horizontalVelocityMultiplier;
	private int numberOfKills;
	private final AudioManager audioManager;

	public UserPlane(int initialHealth) {
		super(IMAGE_NAME, IMAGE_HEIGHT, INITIAL_X_POSITION, INITIAL_Y_POSITION, initialHealth);
		verticalVelocityMultiplier = 0;
		horizontalVelocityMultiplier = 0;
		audioManager = AudioManager.getInstance();
	}

	@Override
	public void updatePosition() {
		if (isMoving()) {
			double initialTranslateY = getTranslateY();
			double initialTranslateX = getTranslateX();
			this.moveVertically(VERTICAL_VELOCITY * verticalVelocityMultiplier);
			this.moveHorizontally(HORIZONTAL_VELOCITY * horizontalVelocityMultiplier);
			double newPosition = getLayoutY() + getTranslateY();
			if (newPosition < Y_UPPER_BOUND || newPosition > Y_LOWER_BOUND) {
				this.setTranslateY(initialTranslateY);
			}
		}
	}

	@Override
	public void takeDamage() {
		super.takeDamage();  // Call to parent method for damage logic

		// Apply the screen shake effect after damage
		Platform.runLater(() -> {
			javafx.scene.Node rootNode = getSceneRootNode();  // Assuming this method correctly gets the root node
			if (rootNode != null) {
				shakeScreen(rootNode);  // Trigger screen shake
			}
		});
	}

	@Override
	public void updateActor() {
		updatePosition();
	}

	@Override
	public ActiveActorDestructible fireProjectile() {
		audioManager.playSoundEffect(SHOOTING_SFX_PATH);

		double PROJECTILE_X_POSITION = getLayoutX() + getTranslateX() + 110;
		return new UserProjectile(PROJECTILE_X_POSITION, getProjectileYPosition(PROJECTILE_Y_POSITION_OFFSET));
	}

	private boolean isMoving() {
		return verticalVelocityMultiplier != 0 || horizontalVelocityMultiplier != 0;
	}

	public void moveUp() {
		verticalVelocityMultiplier = -1;
	}

	public void moveDown() {
		verticalVelocityMultiplier = 1;
	}

	public void moveForward() {
		horizontalVelocityMultiplier = 1;
	}

	public void moveBackward() {
		horizontalVelocityMultiplier = -1;
	}

	public void stopVerticalMovement() {
		verticalVelocityMultiplier = 0;
	}

	public void stopHorizontalMovement() {
		horizontalVelocityMultiplier = 0;
	}

	public int getNumberOfKills() {
		return numberOfKills;
	}

	public void incrementKillCount() {
		numberOfKills++;
	}
	private void shakeScreen(javafx.scene.Node rootNode) {
		// Ensure that the rootNode is an instance of a node that can be translated
		if (rootNode != null) {
			final double SHAKE_DISTANCE = 10.0;  // Maximum shake distance
			final int SHAKE_TIMES = 5;           // Number of shake iterations
			final int SHAKE_DURATION_MS = 100;   // Duration per shake
			final double INITIAL_X = rootNode.getTranslateX();
			final double INITIAL_Y = rootNode.getTranslateY();

			// Create the shake effect
			TranslateTransition[] shakes = new TranslateTransition[SHAKE_TIMES];
			for (int i = 0; i < SHAKE_TIMES; i++) {
				double offsetX = (Math.random() * 2 - 1) * SHAKE_DISTANCE;  // Random horizontal offset
				double offsetY = (Math.random() * 2 - 1) * SHAKE_DISTANCE;  // Random vertical offset

				shakes[i] = new TranslateTransition(Duration.millis(SHAKE_DURATION_MS), rootNode);
				shakes[i].setByX(offsetX);  // Apply offset on X
				shakes[i].setByY(offsetY);  // Apply offset on Y
				shakes[i].setCycleCount(1);
				shakes[i].setAutoReverse(true);  // Ensure it goes back to original position after shake
			}

			// Clip the root node to prevent shaking content from overflowing
			rootNode.setClip(new javafx.scene.shape.Rectangle(rootNode.getBoundsInLocal().getWidth(), rootNode.getBoundsInLocal().getHeight()));

			// Chain the shake transitions sequentially
			for (int i = 0; i < SHAKE_TIMES; i++) {
				final int index = i;
				shakes[i].setOnFinished(event -> {
					// After each shake, reset position back to the original coordinates
					TranslateTransition resetPosition = new TranslateTransition(Duration.millis(50), rootNode);
					resetPosition.setToX(INITIAL_X);  // Set to the original X position
					resetPosition.setToY(INITIAL_Y);  // Set to the original Y position
					resetPosition.play();

					// If there is a next shake, start it
					if (index + 1 < SHAKE_TIMES) {
						shakes[index + 1].play();
					}
				});
			}

			// Start the first shake
			shakes[0].play();
		}
	}
	private javafx.scene.Node getSceneRootNode() {
		// Get the root node from the scene, which could be a Group or a Pane.
		return getScene().getRoot();  // Return as Node, which can be either Group, Pane, or other types
	}

}
