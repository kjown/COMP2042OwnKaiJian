package com.example.demo.actors;

import com.example.demo.actors.projectiles.UserProjectile;
import com.example.demo.controller.AudioManager;
import com.example.demo.view.LevelView;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;
import javafx.scene.effect.Glow;

/**
 * The UserPlane class represents the user's plane in the game, extending from FighterPlane.
 * It handles user movement, health, projectile firing, and shield activation based on kills.
 */
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

	private static final int KILLS_TO_ACTIVATE_SHIELD = 2;
	private static final int SHIELD_DURATION_MS = 5000;

	private int verticalVelocityMultiplier;
	private int horizontalVelocityMultiplier;
	private int numberOfKills;
	private final AudioManager audioManager;
	private boolean shieldActive;
	private long shieldActivatedTime;

	private Circle shieldCircle;
	private LevelView levelView;

	/**
	 * Constructs a UserPlane object with the specified initial health.
	 * Initializes movement multipliers, shield, and audio manager.
	 *
	 * @param initialHealth The initial health of the plane.
	 */
	public UserPlane(int initialHealth) {
		super(IMAGE_NAME, IMAGE_HEIGHT, INITIAL_X_POSITION, INITIAL_Y_POSITION, initialHealth);
		verticalVelocityMultiplier = 0;
		horizontalVelocityMultiplier = 0;
		audioManager = AudioManager.getInstance();
		shieldActive = false;

		initializeShieldVisual();
	}

	/**
	 * Updates the position of the user plane based on movement multipliers.
	 * Ensures the plane does not move out of the vertical bounds.
	 */
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

		updateShieldPosition();
	}

	/**
	 * Initializes the visual representation of the shield as a circle.
	 * The shield is initially invisible.
	 */
	private void initializeShieldVisual() {
		shieldCircle = new Circle();
		shieldCircle.setRadius(80);
		shieldCircle.setFill(Color.rgb(0, 191, 255, 0.3)); // Blue semi-transparent color
		shieldCircle.setStroke(Color.CYAN);
		shieldCircle.setStrokeWidth(3);
		shieldCircle.setVisible(false);
		shieldCircle.setEffect(new Glow(0.8));

		Platform.runLater(() -> {
			javafx.scene.Node parentNode = this.getParent();
			if (parentNode != null) {
				if (parentNode instanceof Pane) {
					((Pane) parentNode).getChildren().add(shieldCircle);
				} else if (parentNode instanceof javafx.scene.Group) {
					((javafx.scene.Group) parentNode).getChildren().add(shieldCircle);
				}
			}
		});
	}

	/**
	 * Updates the position of the shield based on the user plane's position.
	 * The shield is centered on the plane and follows its movements.
	 */
	private void updateShieldPosition() {
		Platform.runLater(() -> {
			if (shieldCircle != null && shieldCircle.isVisible()) {
				double planeCenterX = getLayoutX() + getTranslateX() + IMAGE_HEIGHT / 2 + 50;
				double planeCenterY = getLayoutY() + getTranslateY() + IMAGE_HEIGHT / 2;

				shieldCircle.setCenterX(planeCenterX);
				shieldCircle.setCenterY(planeCenterY);
			}
		});
	}

	/**
	 * Handles the logic for taking damage, including shield protection and screen shake effect.
	 */
	@Override
	public void takeDamage() {
		if (!shieldActive) {
			super.takeDamage();
		}

		Platform.runLater(() -> {
			javafx.scene.Node rootNode = getSceneRootNode();
			if (rootNode != null) {
				shakeScreen(rootNode);
			}
		});
	}

	/**
	 * Updates the user plane's position and checks the shield status.
	 */
	@Override
	public void updateActor() {
		updatePosition();
		checkShieldStatus();
	}

	/**
	 * Fires a projectile from the user plane.
	 *
	 * @return A new instance of UserProjectile.
	 */
	@Override
	public ActiveActorDestructible fireProjectile() {
		audioManager.playSoundEffect(SHOOTING_SFX_PATH);

		double PROJECTILE_X_POSITION = getLayoutX() + getTranslateX() + 110;
		return new UserProjectile(PROJECTILE_X_POSITION, getProjectileYPosition(PROJECTILE_Y_POSITION_OFFSET));
	}

	/**
	 * Checks if the plane is currently moving based on its velocity multipliers.
	 *
	 * @return true if the plane is moving, false otherwise.
	 */
	private boolean isMoving() {
		return verticalVelocityMultiplier != 0 || horizontalVelocityMultiplier != 0;
	}

	/**
	 * Moves the plane upwards.
	 */
	public void moveUp() {
		verticalVelocityMultiplier = -1;
	}

	/**
	 * Moves the plane downwards.
	 */
	public void moveDown() {
		verticalVelocityMultiplier = 1;
	}

	/**
	 * Moves the plane forward (to the right).
	 */
	public void moveForward() {
		horizontalVelocityMultiplier = 1;
	}

	/**
	 * Moves the plane backward (to the left).
	 */
	public void moveBackward() {
		horizontalVelocityMultiplier = -1;
	}

	/**
	 * Stops the vertical movement of the plane.
	 */
	public void stopVerticalMovement() {
		verticalVelocityMultiplier = 0;
	}

	/**
	 * Stops the horizontal movement of the plane.
	 */
	public void stopHorizontalMovement() {
		horizontalVelocityMultiplier = 0;
	}

	/**
	 * Gets the number of kills achieved by the user plane.
	 *
	 * @return The current kill count.
	 */
	public int getNumberOfKills() {
		return numberOfKills;
	}

	/**
	 * Increments the kill count and checks if the shield should be activated.
	 */
	public void incrementKillCount() {
		numberOfKills++;
		if (numberOfKills % KILLS_TO_ACTIVATE_SHIELD == 0) {
			activateShield();
		}
	}

	/**
	 * Activates the shield for a limited duration.
	 */
	private void activateShield() {
		shieldActive = true;
		shieldActivatedTime = System.currentTimeMillis();
		System.out.println("Shield activated!");
		Platform.runLater(() -> shieldCircle.setVisible(true));
	}

	/**
	 * Checks if the shield duration has expired and deactivates it if necessary.
	 */
	private void checkShieldStatus() {
		if (shieldActive && (System.currentTimeMillis() - shieldActivatedTime) >= SHIELD_DURATION_MS) {
			deactivateShield();
		}
	}

	/**
	 * Deactivates the shield and makes it invisible.
	 */
	private void deactivateShield() {
		shieldActive = false;
		System.out.println("Shield deactivated!");
		Platform.runLater(() -> shieldCircle.setVisible(false));
	}

	/**
	 * Creates a screen shake effect when the plane takes damage.
	 *
	 * @param rootNode The root node of the scene to apply the shake effect on.
	 */
	private void shakeScreen(javafx.scene.Node rootNode) {
		if (rootNode != null) {
			final double SHAKE_DISTANCE = 10.0;
			final int SHAKE_TIMES = 5;
			final int SHAKE_DURATION_MS = 100;
			final double INITIAL_X = rootNode.getTranslateX();
			final double INITIAL_Y = rootNode.getTranslateY();

			// Create the shake effect
			TranslateTransition[] shakes = new TranslateTransition[SHAKE_TIMES];
			for (int i = 0; i < SHAKE_TIMES; i++) {
				double offsetX = (Math.random() * 2 - 1) * SHAKE_DISTANCE;
				double offsetY = (Math.random() * 2 - 1) * SHAKE_DISTANCE;

				shakes[i] = new TranslateTransition(Duration.millis(SHAKE_DURATION_MS), rootNode);
				shakes[i].setByX(offsetX);
				shakes[i].setByY(offsetY);
				shakes[i].setCycleCount(1);
				shakes[i].setAutoReverse(true);
			}

			// Clip the root node to prevent shaking content from overflowing
			rootNode.setClip(new javafx.scene.shape.Rectangle(rootNode.getBoundsInLocal().getWidth(), rootNode.getBoundsInLocal().getHeight()));

			// Chain the shake transitions sequentially
			for (int i = 0; i < SHAKE_TIMES; i++) {
				final int index = i;
				shakes[i].setOnFinished(event -> {
					// After each shake, reset position back to the original coordinates
					TranslateTransition resetPosition = new TranslateTransition(Duration.millis(50), rootNode);
					resetPosition.setToX(INITIAL_X);
					resetPosition.setToY(INITIAL_Y);
					resetPosition.play();

					// If there is a next shake, start it
					if (index + 1 < SHAKE_TIMES) {
						shakes[index + 1].play();
					}
				});
			}

			shakes[0].play();
		}
	}

	/**
	 * Retrieves the root node of the scene to apply the shake effect.
	 *
	 * @return The root node of the scene, or null if the scene is not available.
	 */
	private javafx.scene.Node getSceneRootNode() {
		Scene scene = getScene();
		if (scene != null) {
			Parent root = scene.getRoot();
			return root;
		}
		return null;
	}
}
