package com.example.demo.actors;

import com.example.demo.view.LevelViewLevelBoss;
import com.example.demo.view.ShieldImage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents the Boss character in the game, which extends the {@link FighterPlane} class.
 * The Boss can move vertically, fire projectiles, and activate a shield for defense.
 * The behavior includes randomized movement, shield activation, and projectile firing.
 */
public class Boss extends FighterPlane {

	// Constants
	/** The image file name representing the Boss's appearance. */
	private static final String IMAGE_NAME = "bossplane.png";

	/** The initial X and Y positions for the Boss. */
	private static final double INITIAL_X_POSITION = 1000.0;
	private static final double INITIAL_Y_POSITION = 400;

	/** Offset for projectile spawn Y position. */
	private static final double PROJECTILE_Y_POSITION_OFFSET = 75.0;

	/** The probability that the Boss will fire a projectile in a given frame. */
	private static final double BOSS_FIRE_RATE = 0.04;

	/** The probability that the Boss's shield will be activated in a given frame. */
	private static final double BOSS_SHIELD_PROBABILITY = 0.1;

	/** The height of the Boss's image. */
	private static final int IMAGE_HEIGHT = 70;

	/** The vertical movement velocity for the Boss. */
	private static final int VERTICAL_VELOCITY = 8;

	/** The health of the Boss. */
	private static final int HEALTH = 10;

	/** The health of the Boss's shield when activated. */
	private static final int SHIELD_HEALTH = 50;

	/** The frequency of different vertical movement patterns per cycle. */
	private static final int MOVE_FREQUENCY_PER_CYCLE = 5;

	/** The maximum number of frames allowed for consecutive moves in the same direction. */
	private static final int MAX_FRAMES_WITH_SAME_MOVE = 10;

	/** The vertical position boundaries within which the Boss can move. */
	private static final int Y_POSITION_UPPER_BOUND = -100;
	private static final int Y_POSITION_LOWER_BOUND = 475;

	/** The maximum number of frames the shield can be active before it deactivates. */
	private static final int MAX_FRAMES_WITH_SHIELD = 500;

	/** The damage value taken by the shield upon impact. */
	private static final int SHIELD_DAMAGE_VALUE = 10;

	/** Offsets for the shield's position relative to the Boss. */
	private static final double SHIELD_OFFSET_X = -35;
	private static final double SHIELD_OFFSET_Y = 0;

	// Fields
	/** List storing the movement pattern for the Boss. */
	private final List<Integer> movePattern;

	/** Indicator of whether the Boss's shield is currently active. */
	private boolean isShielded;

	/** Tracks the number of frames with the same movement direction. */
	private int consecutiveMovesInSameDirection;

	/** Index of the current move in the movement pattern. */
	private int indexOfCurrentMove;

	/** Tracks the number of frames the shield has been activated. */
	private int framesWithShieldActivated;

	/** The level view associated with this Boss. */
	private final LevelViewLevelBoss levelView;

	/** The shield image representing the Boss's shield. */
	private final ShieldImage shieldImage;

	/**
	 * Constructs a new Boss object with a specific level view.
	 * The Boss is initialized with default values for movement and shield behavior.
	 *
	 * @param levelView The level view associated with this Boss.
	 */
	public Boss(LevelViewLevelBoss levelView) {
		super(IMAGE_NAME, IMAGE_HEIGHT, INITIAL_X_POSITION, INITIAL_Y_POSITION, HEALTH);
		this.levelView = levelView;
		this.shieldImage = new ShieldImage(INITIAL_X_POSITION, INITIAL_Y_POSITION, SHIELD_HEALTH);
		this.movePattern = new ArrayList<>();
		initializeFields();
		initializeMovePattern();
		addShieldToScene();
	}

	/**
	 * Updates the position of the Boss.
	 * The Boss moves vertically based on the current movement pattern
	 * and resets if it goes out of bounds.
	 */
	@Override
	public void updatePosition() {
		double initialTranslateY = getTranslateY();
		moveVertically(getNextMove());
		if (isOutOfBounds()) {
			resetVerticalPosition(initialTranslateY);
		}
		updateShieldPosition();
	}

	/**
	 * Updates the Boss's state each frame.
	 * This includes updating the position and checking the shield status.
	 */
	@Override
	public void updateActor() {
		updatePosition();
		updateShield();
	}

	/**
	 * Fires a projectile if the conditions are met.
	 *
	 * @return A new {@link BossProjectile} if the Boss decides to fire; otherwise, null.
	 */
	@Override
	public ActiveActorDestructible fireProjectile() {
		return shouldFireProjectile() ? createBossProjectile() : null;
	}

	/**
	 * Handles the logic for the Boss taking damage.
	 * If the shield is active, it absorbs the damage; otherwise, the Boss takes the damage.
	 */
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

	/**
	 * Initializes the Boss's fields to default values.
	 */
	private void initializeFields() {
		this.consecutiveMovesInSameDirection = 0;
		this.indexOfCurrentMove = 0;
		this.framesWithShieldActivated = 0;
		this.isShielded = false;
	}

	/**
	 * Adds the shield image to the game's scene.
	 */
	private void addShieldToScene() {
		levelView.getRoot().getChildren().add(shieldImage);
	}

	/**
	 * Initializes the movement pattern for the Boss.
	 * The pattern is randomized and includes vertical movement variations.
	 */
	private void initializeMovePattern() {
		for (int i = 0; i < MOVE_FREQUENCY_PER_CYCLE; i++) {
			movePattern.add(VERTICAL_VELOCITY);
			movePattern.add(-VERTICAL_VELOCITY);
			movePattern.add(0);
		}
		Collections.shuffle(movePattern);
	}

	/**
	 * Updates the shield's status each frame.
	 * It manages shield activation, deactivation, and duration.
	 */
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

	/**
	 * Determines the next move direction for the Boss.
	 * The direction is chosen based on the current move pattern.
	 *
	 * @return The next move direction as an integer.
	 */
	private int getNextMove() {
		int currentMove = movePattern.get(indexOfCurrentMove);
		consecutiveMovesInSameDirection++;
		if (consecutiveMovesInSameDirection >= MAX_FRAMES_WITH_SAME_MOVE) {
			reshuffleMovePattern();
		}
		return currentMove;
	}

	/**
	 * Reshuffles the movement pattern to add randomness.
	 */
	private void reshuffleMovePattern() {
		Collections.shuffle(movePattern);
		consecutiveMovesInSameDirection = 0;
		indexOfCurrentMove = (indexOfCurrentMove + 1) % movePattern.size();
	}

	/**
	 * Checks if the Boss should fire a projectile based on a random chance.
	 *
	 * @return True if the Boss should fire; otherwise, false.
	 */
	private boolean shouldFireProjectile() {
		return Math.random() < BOSS_FIRE_RATE;
	}

	/**
	 * Creates a new projectile fired by the Boss.
	 *
	 * @return A new instance of {@link BossProjectile}.
	 */
	private ActiveActorDestructible createBossProjectile() {
		return new BossProjectile(calculateProjectileYPosition());
	}

	/**
	 * Calculates the initial Y position for the Boss's projectile.
	 *
	 * @return The Y position for the projectile.
	 */
	private double calculateProjectileYPosition() {
		return getLayoutY() + getTranslateY() + PROJECTILE_Y_POSITION_OFFSET;
	}

	/**
	 * Determines if the shield should be activated based on a random chance.
	 *
	 * @return True if the shield should activate; otherwise, false.
	 */
	private boolean shouldActivateShield() {
		return Math.random() < BOSS_SHIELD_PROBABILITY;
	}

	/**
	 * Checks if the shield's duration has exceeded its maximum limit.
	 *
	 * @return True if the shield duration is exceeded; otherwise, false.
	 */
	private boolean isShieldDurationExceeded() {
		return framesWithShieldActivated >= MAX_FRAMES_WITH_SHIELD;
	}

	/**
	 * Activates the Boss's shield, making it visible and ready to absorb damage.
	 */
	private void activateShield() {
		isShielded = true;
		shieldImage.showShield();
	}

	/**
	 * Deactivates the Boss's shield, removing its protective effects.
	 */
	private void deactivateShield() {
		isShielded = false;
		framesWithShieldActivated = 0;
		shieldImage.hideShield();
	}

	/**
	 * Updates the shield's position to follow the Boss's current position.
	 */
	private void updateShieldPosition() {
		shieldImage.setPosition(getLayoutX() + getTranslateX() + SHIELD_OFFSET_X, getLayoutY() + getTranslateY() + SHIELD_OFFSET_Y);
	}

	/**
	 * Checks if the Boss has moved outside the vertical bounds of the game area.
	 *
	 * @return True if the Boss is out of bounds, otherwise false.
	 */
	private boolean isOutOfBounds() {
		double currentPosition = getLayoutY() + getTranslateY();
		return currentPosition < Y_POSITION_UPPER_BOUND || currentPosition > Y_POSITION_LOWER_BOUND;
	}

	/**
	 * Resets the vertical position of the Boss to its initial position if it goes out of bounds.
	 *
	 * @param initialTranslateY The Y position to reset the Boss to.
	 */
	private void resetVerticalPosition(double initialTranslateY) {
		setTranslateY(initialTranslateY);
	}
}