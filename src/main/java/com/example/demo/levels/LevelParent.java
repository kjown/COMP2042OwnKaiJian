package com.example.demo.levels;

import java.util.*;
import java.util.stream.Collectors;

import com.example.demo.menu.EndMenu;
import com.example.demo.actors.Helicopter;
import com.example.demo.controller.Controller;
import com.example.demo.view.LevelView;
import com.example.demo.actors.ActiveActorDestructible;
import com.example.demo.actors.FighterPlane;
import com.example.demo.actors.UserPlane;
import javafx.animation.*;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.*;
import javafx.stage.Stage;
import javafx.scene.input.*;
import javafx.util.Duration;
import com.example.demo.controller.AudioManager;

/**
 * The parent class for all levels in the game. It manages the overall game loop,
 * actors' behaviors, projectiles, collisions, and game state.
 * This abstract class provides methods for initializing levels, handling user inputs,
 * managing actors (such as user and enemy planes), and determining the game outcome.
 * It is extended by specific level classes to implement level-specific behavior.
 */

public abstract class LevelParent extends Observable {

	private static final double SCREEN_HEIGHT_ADJUSTMENT = 150;
	private static final int MILLISECOND_DELAY = 50;
	private final double screenHeight;
	private final double screenWidth;
	private final double enemyMaximumYPosition;

	private final Group root;
	private final Timeline timeline;
	private final UserPlane user;
	private final Scene scene;
	private final ImageView background;

	private final List<ActiveActorDestructible> friendlyUnits;
	private final List<ActiveActorDestructible> enemyUnits;
	private final List<ActiveActorDestructible> userProjectiles;
	private final List<ActiveActorDestructible> enemyProjectiles;

	private int currentNumberOfEnemies;
	private LevelView levelView;
	private boolean isSPaceEnabled= true;
	private boolean isPause = false;
	private boolean isESCEnabled= true;
	private final Controller controller;
	private final Stage stage;
	private static final String WIN_SOUND_PATH = "/com/example/demo/music/winsound.wav";
	private static final String LOSE_SOUND_PATH = "/com/example/demo/music/losesound.wav";
	private final AudioManager audioManager;

	/**
	 * Constructs a LevelParent object to set up the basic game parameters, such as the background, screen dimensions,
	 * and initializing actors like the player and enemies.
	 *
	 * @param backgroundImageName The file path for the background image of the level.
	 * @param screenHeight The height of the game screen.
	 * @param screenWidth The width of the game screen.
	 * @param playerInitialHealth The initial health of the user plane.
	 * @param controller The game controller that manages the state of the game.
	 * @param stage The stage for the game window.
	 */
	public LevelParent(String backgroundImageName, double screenHeight, double screenWidth, int playerInitialHealth, Controller controller, Stage stage) {
		this.root = new Group();
		this.scene = new Scene(root, screenWidth, screenHeight);
		this.timeline = new Timeline();
		this.user = new UserPlane(playerInitialHealth);
		this.friendlyUnits = new ArrayList<>();
		this.enemyUnits = new ArrayList<>();
		this.userProjectiles = new ArrayList<>();
		this.enemyProjectiles = new ArrayList<>();

		this.background = new ImageView(new Image(getClass().getResource(backgroundImageName).toExternalForm()));
		this.screenHeight = screenHeight;
		this.screenWidth = screenWidth;
		this.enemyMaximumYPosition = screenHeight - SCREEN_HEIGHT_ADJUSTMENT;
		this.levelView = instantiateLevelView();
		this.currentNumberOfEnemies = 0;

		this.controller = controller;
		this.stage = stage;

		initializeTimeline();
		friendlyUnits.add(user);
		audioManager = AudioManager.getInstance();
	}

	/**
	 * Abstract method to initialize the friendly units for the level (i.e., player-controlled objects).
	 * This needs to be implemented by subclasses to add specific friendly units for each level.
	 */
	protected abstract void initializeFriendlyUnits();

	/**
	 * Abstract method to check if the game is over. This is specific to the level.
	 * Subclasses should define their own logic for when the game ends.
	 */
	protected abstract void checkIfGameOver();

	/**
	 * Abstract method to spawn enemy units for the level.
	 * This is level-specific and should be implemented by subclasses.
	 */
	protected abstract void spawnEnemyUnits();

	/**
	 * Abstract method to instantiate the LevelView for the level. This allows customizing the UI for each level.
	 *
	 * @return The instantiated LevelView object.
	 */
	protected abstract LevelView instantiateLevelView();

	/**
	 * Initializes the scene, adds background, and sets up friendly units.
	 *
	 * @return The scene for this level.
	 */
	public Scene initializeScene() {
		initializeBackground();
		initializeFriendlyUnits();
		levelView.showHeartDisplay();
		return scene;
	}

	/**
	 * Starts the game loop and allows user input to control the player.
	 */
	public void startGame() {
		background.requestFocus();
		timeline.play();
	}

	/**
	 * Notifies observers that the player has completed the level and the game is transitioning to the next level.
	 *
	 * @param levelName The name of the next level.
	 */
	public void goToNextLevel(String levelName) {
		setChanged();
		notifyObservers(levelName);
	}

	/**
	 * Updates the scene, including spawning enemies, updating actors, handling collisions, and checking game status.
	 */
	private void updateScene() {
		spawnEnemyUnits();
		updateActors();
		generateEnemyFire();
		updateNumberOfEnemies();
		handleEnemyPenetration();
		handleUserProjectileCollisions();
		handleEnemyProjectileCollisions();
		handlePlaneCollisions();
		handleProjectileCollisions();
		removeAllDestroyedActors();
		updateKillCount();
		updateLevelView();
		checkIfGameOver();
	}

	/**
	 * Initializes the timeline and sets up the game loop.
	 */
	private void initializeTimeline() {
		timeline.setCycleCount(Timeline.INDEFINITE);
		KeyFrame gameLoop = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> updateScene());
		timeline.getKeyFrames().add(gameLoop);
	}

	/**
	 * Initializes the background of the level and sets up key event handlers for user input.
	 */
	private void initializeBackground() {
		background.setFocusTraversable(true);
		background.setFitHeight(screenHeight);
		background.setFitWidth(screenWidth);
		background.setOnKeyPressed(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent e) {
				KeyCode kc = e.getCode();
				if (kc == KeyCode.UP) user.moveUp();
				if (kc == KeyCode.DOWN) user.moveDown();
				if (kc == KeyCode.RIGHT) user.moveForward();
				if (kc == KeyCode.LEFT) user.moveBackward();
				if (kc == KeyCode.SPACE) fireProjectile();
				if (kc == KeyCode.ESCAPE && isESCEnabled) pauseLevel();
			}
		});
		background.setOnKeyReleased(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent e) {
				KeyCode kc = e.getCode();
				if (kc == KeyCode.UP || kc == KeyCode.DOWN) user.stopVerticalMovement();
				if (kc == KeyCode.LEFT || kc == KeyCode.RIGHT) user.stopHorizontalMovement();
			}
		});
		root.getChildren().add(background);
	}

	/**
	 * Fires a projectile from the user plane and adds it to the game.
	 */
	private void fireProjectile() {
		ActiveActorDestructible projectile = user.fireProjectile();
		root.getChildren().add(projectile);
		userProjectiles.add(projectile);
	}

	/**
	 * Generates enemy fire by having enemy units shoot projectiles.
	 */
	private void generateEnemyFire() {
		enemyUnits.forEach(enemy -> {
			if (enemy instanceof FighterPlane) {
				ActiveActorDestructible projectile = ((FighterPlane) enemy).fireProjectile();
				spawnEnemyProjectile(projectile);
			} else if (enemy instanceof Helicopter) {
				ActiveActorDestructible projectile = ((Helicopter) enemy).fireProjectile();
				spawnEnemyProjectile(projectile);
			}
		});	}

	/**
	 * Spawns a new enemy projectile and adds it to the game.
	 *
	 * @param projectile The projectile to spawn.
	 */
	private void spawnEnemyProjectile(ActiveActorDestructible projectile) {
		if (projectile != null) {
			root.getChildren().add(projectile);
			enemyProjectiles.add(projectile);
		}
	}

	/**
	 * Updates all actors (friendly units, enemy units, projectiles) by calling their updateActor method.
	 */
	private void updateActors() {
		friendlyUnits.forEach(plane -> plane.updateActor());
		enemyUnits.forEach(enemy -> enemy.updateActor());
		userProjectiles.forEach(projectile -> projectile.updateActor());
		enemyProjectiles.forEach(projectile -> projectile.updateActor());
	}

	/**
	 * Removes destroyed actors from the game and updates the root.
	 */
	private void removeAllDestroyedActors() {
		removeDestroyedActors(friendlyUnits);
		removeDestroyedActors(enemyUnits);
		removeDestroyedActors(userProjectiles);
		removeDestroyedActors(enemyProjectiles);
	}

	/**
	 * Removes destroyed actors from the provided list and from the root.
	 *
	 * @param actors The list of actors to check for destruction.
	 */
	private void removeDestroyedActors(List<ActiveActorDestructible> actors) {
		List<ActiveActorDestructible> destroyedActors = actors.stream().filter(actor -> actor.isDestroyed())
				.collect(Collectors.toList());
		root.getChildren().removeAll(destroyedActors);
		actors.removeAll(destroyedActors);
	}

	/**
	 * Handles collisions between friendly units (e.g., user plane) and enemy units.
	 */
	private void handlePlaneCollisions() {
		handleCollisions(friendlyUnits, enemyUnits);
	}

	/**
	 * Handles collisions between enemy projectiles and user projectiles.
	 */
	private void handleProjectileCollisions() {
		handleCollisions(enemyProjectiles, userProjectiles);
	}

	/**
	 * Handles collisions between user projectiles and enemy units.
	 */
	private void handleUserProjectileCollisions() {
		handleCollisions(userProjectiles, enemyUnits);
	}

	/**
	 * Handles collisions between enemy projectiles and friendly units.
	 */
	private void handleEnemyProjectileCollisions() {
		handleCollisions(enemyProjectiles, friendlyUnits);
	}

	/**
	 * Handles collisions between two lists of actors and applies damage to both actors if they collide.
	 * @param actors1 the first list of actors
	 * @param actors2 the second list of actors
	 */
	private void handleCollisions(List<ActiveActorDestructible> actors1,
			List<ActiveActorDestructible> actors2) {
		for (ActiveActorDestructible actor : actors2) {
			for (ActiveActorDestructible otherActor : actors1) {
				if (actor.getBoundsInParent().intersects(otherActor.getBoundsInParent())) {
					actor.takeDamage();
					otherActor.takeDamage();
				}
			}
		}
	}

	/**
	 * Checks if any enemy units have penetrated the user's defenses (i.e., crossed the screen boundary).
	 * If so, the user takes damage and the enemy is destroyed.
	 */
	private void handleEnemyPenetration() {
		for (ActiveActorDestructible enemy : enemyUnits) {
			if (enemyHasPenetratedDefenses(enemy)) {
				user.takeDamage();
				enemy.destroy();
			}
		}
	}

	/**
	 * Updates the level view by removing hearts from the display based on the user's current health.
	 */
	private void updateLevelView() {
		levelView.removeHearts(user.getHealth());
	}

	/**
	 * Updates the kill count by incrementing the user's kill count for each enemy defeated.
	 */
	private void updateKillCount() {
		for (int i = 0; i < currentNumberOfEnemies - enemyUnits.size(); i++) {
			user.incrementKillCount();
		}
	}

	/**
	 * Checks if an enemy has penetrated the user's defenses (i.e., moved off-screen).
	 * @param enemy the enemy to check
	 * @return true if the enemy has penetrated the defenses, false otherwise
	 */
	private boolean enemyHasPenetratedDefenses(ActiveActorDestructible enemy) {
		return Math.abs(enemy.getTranslateX()) > screenWidth;
	}

	/**
	 * Handles the win condition of the game, stopping the game, displaying a win image, and playing a sound effect.
	 */
	protected void winGame() {
		isESCEnabled= false;
		isSPaceEnabled = false;
		timeline.stop();
		levelView.showWinImage();
		audioManager.playSoundEffect(WIN_SOUND_PATH);
		audioManager.pauseBackgroundMusic();

		EndMenu endMenu = new EndMenu(stage, (int) screenWidth, (int) screenHeight, controller);

		Scene scene = getRoot().getScene();
		scene.setOnKeyPressed(event -> {
			if (event.getCode() == KeyCode.ESCAPE) {
				System.exit(0);
			}
		});
	}

	/**
	 * Handles the lose condition of the game, stopping the game, displaying a game over image, and playing a sound effect.
	 */
	protected void loseGame() {
		isESCEnabled= false;
		isSPaceEnabled = false;
		timeline.stop();
		audioManager.pauseBackgroundMusic();
		audioManager.playSoundEffect(LOSE_SOUND_PATH);

		// Show EndMenu
		EndMenu endMenu = new EndMenu(stage, (int) screenWidth, (int) screenHeight, controller);
		endMenu.show();
	}

	/**
	 * Stops the game by stopping the timeline (pausing the game loop).
	 */
	public void stopGame() {
		timeline.stop();
	}

	/**
	 * Retrieves the user plane object.
	 * @return the user plane
	 */
	protected UserPlane getUser() {
		return user;
	}

	/**
	 * Retrieves the root group of the scene.
	 * @return the root group
	 */
	protected Group getRoot() {
		return root;
	}

	/**
	 * Retrieves the current number of enemies in the game.
	 * @return the current number of enemies
	 */
	protected int getCurrentNumberOfEnemies() {
		return enemyUnits.size();
	}

	/**
	 * Adds an enemy unit to the game and the root scene.
	 * @param enemy the enemy unit to add
	 */
	protected void addEnemyUnit(ActiveActorDestructible enemy) {
		enemyUnits.add(enemy);
		root.getChildren().add(enemy);
	}

	/**
	 * Retrieves the maximum Y position for enemy units.
	 * @return the maximum Y position for enemy units
	 */
	protected double getEnemyMaximumYPosition() {
		return enemyMaximumYPosition;
	}

	/**
	 * Retrieves the screen width of the game.
	 * @return the screen width
	 */
	protected double getScreenWidth() {
		return screenWidth;
	}

	/**
	 * Checks if the user plane is destroyed.
	 * @return true if the user plane is destroyed, false otherwise
	 */
	protected boolean userIsDestroyed() {
		return user.isDestroyed();
	}

	/**
	 * Updates the number of enemies in the game by checking the size of the enemy units list.
	 */
	private void updateNumberOfEnemies() {
		currentNumberOfEnemies = enemyUnits.size();
	}

	/**
	 * Retrieves the controller for the game.
	 * @return the controller
	 */
	public Controller getController() {
		return controller;
	}

	/**
	 * Retrieves the stage for the game window.
	 * @return the stage
	 */
	public Stage getStage() {
		return stage;
	}

	/**
	 * Pauses or resumes the level based on the current state.
	 * If the level is paused, it displays the pause menu and stops background music.
	 * If resumed, it hides the pause menu and resumes background music.
	 */
	private void pauseLevel() {
		if (!isPause) {
			isPause = true;
			isSPaceEnabled=false;
			timeline.pause();
			levelView.showPauseMenuImage();
			controller.pauseBackgroundMusic();
		}

		else {
			isPause = false;
			isSPaceEnabled=true;
			timeline.play();
			levelView.hidePauseMenuImage();
			if (controller.isBackgroundMusicOn()) {
				controller.resumeBackgroundMusic();
			}
		}
	}
}
