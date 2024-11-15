package com.example.demo.actors;

import com.example.demo.view.LevelViewLevelTwo;
import com.example.demo.view.ShieldImage;
import java.util.*;

public class Boss extends FighterPlane {

	// Constants for configuration
	private static final String IMAGE_NAME = "bossplane.png";
	private static final double INITIAL_X_POSITION = 1000.0;
	private static final double INITIAL_Y_POSITION = 400.0;
	private static final double PROJECTILE_Y_POSITION_OFFSET = 75.0;
	private static final double BOSS_FIRE_RATE = 0.04;
	private static final double BOSS_SHIELD_PROBABILITY = 0.1;
	private static final int IMAGE_HEIGHT = 70;
	private static final int VERTICAL_VELOCITY = 8;
	private static final int HEALTH = 10;
	private static final int SHIELD_HEALTH = 50;
	private static final int MOVE_FREQUENCY_PER_CYCLE = 5;
	private static final int ZERO = 0;
	private static final int MAX_FRAMES_WITH_SAME_MOVE = 10;
	private static final int Y_POSITION_UPPER_BOUND = -100;
	private static final int Y_POSITION_LOWER_BOUND = 475;
	private static final int MAX_FRAMES_WITH_SHIELD = 500;
	private static final int SHIELD_DAMAGE = 10;
	private static final double SHIELD_OFFSET_X = -35;
	private static final double SHIELD_OFFSET_Y = 0;

	// Fields
	private final List<Integer> movePattern;
	private boolean isShielded;
	private int consecutiveMovesInSameDirection;
	private int indexOfCurrentMove;
	private int framesWithShieldActivated;
	private final LevelViewLevelTwo levelView;
	private final ShieldImage shieldImage;

	public Boss(LevelViewLevelTwo levelView) {
		super(IMAGE_NAME, IMAGE_HEIGHT, INITIAL_X_POSITION, INITIAL_Y_POSITION, HEALTH);
		this.levelView = levelView;
		this.shieldImage = new ShieldImage(INITIAL_X_POSITION, INITIAL_Y_POSITION, SHIELD_HEALTH);
		this.movePattern = new ArrayList<>();
		this.consecutiveMovesInSameDirection = 0;
		this.indexOfCurrentMove = 0;
		this.framesWithShieldActivated = 0;
		this.isShielded = false;

		initializeMovePattern();
		levelView.getRoot().getChildren().add(shieldImage);
	}

	@Override
	public void updatePosition() {
		double initialTranslateY = getTranslateY();
		moveVertically(getNextMove());
		if (isOutOfBounds()) {
			setTranslateY(initialTranslateY); // Reset position if out of bounds
		}
		updateShieldPosition();
	}

	@Override
	public void updateActor() {
		updatePosition();
		updateShield();
	}

	@Override
	public ActiveActorDestructible fireProjectile() {
		return shouldFireProjectile() ? createBossProjectile() : null;
	}

	@Override
	public void takeDamage() {
		if (isShielded) {
			shieldImage.takeDamage(SHIELD_DAMAGE);
			if (shieldImage.getHealth() <= 0) {
				deactivateShield();
			}
		} else {
			super.takeDamage();
		}
	}

	// Initializes the move pattern with a shuffled list
	private void initializeMovePattern() {
		for (int i = 0; i < MOVE_FREQUENCY_PER_CYCLE; i++) {
			movePattern.add(VERTICAL_VELOCITY);
			movePattern.add(-VERTICAL_VELOCITY);
			movePattern.add(ZERO);
		}
		Collections.shuffle(movePattern);
	}

	// Check if the boss's current move keeps it in bounds
	private boolean isOutOfBounds() {
		double currentPosition = getLayoutY() + getTranslateY();
		return currentPosition < Y_POSITION_UPPER_BOUND || currentPosition > Y_POSITION_LOWER_BOUND;
	}

	// Handles shield behavior updates
	private void updateShield() {
		if (isShielded) {
			framesWithShieldActivated++;
			if (isShieldExhausted()) {
				deactivateShield();
			}
		} else if (shouldActivateShield()) {
			activateShield();
		}
	}

	// Determines the next move direction
	private int getNextMove() {
		int currentMove = movePattern.get(indexOfCurrentMove);
		consecutiveMovesInSameDirection++;
		if (consecutiveMovesInSameDirection == MAX_FRAMES_WITH_SAME_MOVE) {
			resetMovePattern();
		}
		return currentMove;
	}

	// Resets the move pattern to keep things unpredictable
	private void resetMovePattern() {
		Collections.shuffle(movePattern);
		consecutiveMovesInSameDirection = 0;
		indexOfCurrentMove = (indexOfCurrentMove + 1) % movePattern.size();
	}

	// Determines if the boss should fire a projectile in the current frame
	private boolean shouldFireProjectile() {
		return Math.random() < BOSS_FIRE_RATE;
	}

	// Creates a new BossProjectile instance
	private ActiveActorDestructible createBossProjectile() {
		return new BossProjectile(getProjectileInitialPosition());
	}

	// Calculates the initial position for the projectile
	private double getProjectileInitialPosition() {
		return getLayoutY() + getTranslateY() + PROJECTILE_Y_POSITION_OFFSET;
	}

	// Determines if the shield should be activated
	private boolean shouldActivateShield() {
		return Math.random() < BOSS_SHIELD_PROBABILITY;
	}

	// Checks if the shield duration has been exhausted
	private boolean isShieldExhausted() {
		return framesWithShieldActivated >= MAX_FRAMES_WITH_SHIELD;
	}

	// Activates the shield
	private void activateShield() {
		isShielded = true;
		shieldImage.showShield();
	}

	// Deactivates the shield
	private void deactivateShield() {
		isShielded = false;
		framesWithShieldActivated = 0;
		shieldImage.hideShield();
	}

	// Updates the shield's position to stay in front of the Boss Plane
	private void updateShieldPosition() {
		shieldImage.setPosition(getLayoutX() + getTranslateX() + SHIELD_OFFSET_X, getLayoutY() + getTranslateY() + SHIELD_OFFSET_Y);
	}
}
