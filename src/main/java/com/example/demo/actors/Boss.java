package com.example.demo.actors;

import com.example.demo.view.LevelViewLevelBoss;
import com.example.demo.view.ShieldImage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Boss extends FighterPlane {

	// Constants
	private static final String IMAGE_NAME = "bossplane.png";
	private static final double INITIAL_X_POSITION = 1000.0;
	private static final double INITIAL_Y_POSITION = 400;
	private static final double PROJECTILE_Y_POSITION_OFFSET = 75.0;
	private static final double BOSS_FIRE_RATE = 0.04;
	private static final double BOSS_SHIELD_PROBABILITY = 0.1;
	private static final int IMAGE_HEIGHT = 70;
	private static final int VERTICAL_VELOCITY = 8;
	private static final int HEALTH = 10;
	private static final int SHIELD_HEALTH = 50;
	private static final int MOVE_FREQUENCY_PER_CYCLE = 5;
	private static final int MAX_FRAMES_WITH_SAME_MOVE = 10;
	private static final int Y_POSITION_UPPER_BOUND = -100;
	private static final int Y_POSITION_LOWER_BOUND = 475;
	private static final int MAX_FRAMES_WITH_SHIELD = 500;
	private static final int SHIELD_DAMAGE_VALUE = 10;
	private static final double SHIELD_OFFSET_X = -35;
	private static final double SHIELD_OFFSET_Y = 0;

	// Fields
	private final List<Integer> movePattern;
	private boolean isShielded;
	private int consecutiveMovesInSameDirection;
	private int indexOfCurrentMove;
	private int framesWithShieldActivated;
	private final LevelViewLevelBoss levelView;
	private final ShieldImage shieldImage;

	// Constructor
	public Boss(LevelViewLevelBoss levelView) {
		super(IMAGE_NAME, IMAGE_HEIGHT, INITIAL_X_POSITION, INITIAL_Y_POSITION, HEALTH);
		this.levelView = levelView;
		this.shieldImage = new ShieldImage(INITIAL_X_POSITION, INITIAL_Y_POSITION, SHIELD_HEALTH);
		this.movePattern = new ArrayList<>();
		initializeFields();
		initializeMovePattern();
		addShieldToScene();
	}

	@Override
	public void updatePosition() {
		double initialTranslateY = getTranslateY();
		moveVertically(getNextMove());
		if (isOutOfBounds()) {
			resetVerticalPosition(initialTranslateY);
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
			shieldImage.takeDamage(SHIELD_DAMAGE_VALUE);
			if (shieldImage.getHealth() <= 0) {
				deactivateShield();
			}
		} else {
			super.takeDamage();
		}
	}

	// Initializes fields to default values
	private void initializeFields() {
		this.consecutiveMovesInSameDirection = 0;
		this.indexOfCurrentMove = 0;
		this.framesWithShieldActivated = 0;
		this.isShielded = false;
	}

	// Adds the shield to the game scene
	private void addShieldToScene() {
		levelView.getRoot().getChildren().add(shieldImage);
	}

	// Initializes the movement pattern for the boss
	private void initializeMovePattern() {
		for (int i = 0; i < MOVE_FREQUENCY_PER_CYCLE; i++) {
			movePattern.add(VERTICAL_VELOCITY);
			movePattern.add(-VERTICAL_VELOCITY);
			movePattern.add(0);
		}
		Collections.shuffle(movePattern);
	}

	// Updates the shield status
	private void updateShield() {
		if (isShielded) {
			framesWithShieldActivated++;
		} else if (shouldActivateShield()) {
			activateShield();
		}
		if (isShieldDurationExceeded()) {
			deactivateShield();
		}
	}

	// Determines the next movement direction for the boss
	private int getNextMove() {
		int currentMove = movePattern.get(indexOfCurrentMove);
		consecutiveMovesInSameDirection++;
		if (consecutiveMovesInSameDirection >= MAX_FRAMES_WITH_SAME_MOVE) {
			reshuffleMovePattern();
		}
		return currentMove;
	}

	// Reshuffles the movement pattern
	private void reshuffleMovePattern() {
		Collections.shuffle(movePattern);
		consecutiveMovesInSameDirection = 0;
		indexOfCurrentMove = (indexOfCurrentMove + 1) % movePattern.size();
	}

	// Checks if the boss should fire a projectile
	private boolean shouldFireProjectile() {
		return Math.random() < BOSS_FIRE_RATE;
	}

	// Creates a new boss projectile
	private ActiveActorDestructible createBossProjectile() {
		return new BossProjectile(calculateProjectileYPosition());
	}

	// Calculates the Y position for the projectile spawn
	private double calculateProjectileYPosition() {
		return getLayoutY() + getTranslateY() + PROJECTILE_Y_POSITION_OFFSET;
	}

	// Checks if the shield should be activated
	private boolean shouldActivateShield() {
		return Math.random() < BOSS_SHIELD_PROBABILITY;
	}

	// Checks if the shield duration is exceeded
	private boolean isShieldDurationExceeded() {
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

	// Updates the shield's position to match the boss's position
	private void updateShieldPosition() {
		shieldImage.setPosition(getLayoutX() + getTranslateX() + SHIELD_OFFSET_X, getLayoutY() + getTranslateY() + SHIELD_OFFSET_Y);
	}

	// Checks if the boss is out of vertical bounds
	private boolean isOutOfBounds() {
		double currentPosition = getLayoutY() + getTranslateY();
		return currentPosition < Y_POSITION_UPPER_BOUND || currentPosition > Y_POSITION_LOWER_BOUND;
	}

	// Resets the vertical position of the boss if out of bounds
	private void resetVerticalPosition(double initialTranslateY) {
		setTranslateY(initialTranslateY);
	}
}
