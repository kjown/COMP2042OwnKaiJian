package com.example.demo.actors;

import com.example.demo.controller.AudioManager;

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
	private static final String SHOOTING_SFX_PATH = "/com/example/demo/music/shootsound.wav";  // Path to the shooting sound

	private int verticalVelocityMultiplier;
	private int horizontalVelocityMultiplier;
	private int numberOfKills;
	private final AudioManager audioManager;

	public UserPlane(int initialHealth) {
		super(IMAGE_NAME, IMAGE_HEIGHT, INITIAL_X_POSITION, INITIAL_Y_POSITION, initialHealth);
		verticalVelocityMultiplier = 0;
		horizontalVelocityMultiplier = 0;
		audioManager = AudioManager.getInstance();  // Initialize AudioManager instance
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
	public void updateActor() {
		updatePosition();
	}

	@Override
	public ActiveActorDestructible fireProjectile() {
		// Play the shooting sound effect
		audioManager.playSoundEffect(SHOOTING_SFX_PATH);

		// Create and return a new projectile
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

}
