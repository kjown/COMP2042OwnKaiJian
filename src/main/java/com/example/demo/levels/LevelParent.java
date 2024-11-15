package com.example.demo.levels;

import java.util.*;
import java.util.stream.Collectors;

import com.example.demo.menu.EndMenu;
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

public abstract class LevelParent extends Observable {

	private static final double SCREEN_HEIGHT_ADJUSTMENT = 150;
	private static final int MILLISECOND_DELAY = 50;

	private final double screenHeight;
	private final double screenWidth;
	private final double enemyMaxYPosition;

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
	private boolean isSpaceEnabled = true;
	private boolean isPaused = false;
	private boolean isESCEnabled = true;
	private final Controller controller;
	private final Stage stage;

	public LevelParent(String backgroundImageName, double screenHeight, double screenWidth, int playerInitialHealth, Controller controller, Stage stage) {
		this.screenHeight = screenHeight;
		this.screenWidth = screenWidth;
		this.enemyMaxYPosition = screenHeight - SCREEN_HEIGHT_ADJUSTMENT;

		this.root = new Group();
		this.scene = new Scene(root, screenWidth, screenHeight);
		this.timeline = new Timeline();
		this.user = new UserPlane(playerInitialHealth);

		this.friendlyUnits = new ArrayList<>();
		this.enemyUnits = new ArrayList<>();
		this.userProjectiles = new ArrayList<>();
		this.enemyProjectiles = new ArrayList<>();

		this.background = loadBackgroundImage(backgroundImageName);
		this.levelView = instantiateLevelView();
		this.currentNumberOfEnemies = 0;

		this.controller = controller;
		this.stage = stage;

		initializeTimeline();
		initializeBackground();
		friendlyUnits.add(user);
	}

	protected abstract void initializeFriendlyUnits();
	protected abstract void checkIfGameOver();
	protected abstract void spawnEnemyUnits();
	protected abstract LevelView instantiateLevelView();

	public Scene initializeScene() {
		initializeFriendlyUnits();
		levelView.showHeartDisplay();
		return scene;
	}

	public void startGame() {
		background.requestFocus();
		timeline.play();
	}

	public void goToNextLevel(String levelName) {
		setChanged();
		notifyObservers(levelName);
	}

	private ImageView loadBackgroundImage(String backgroundImageName) {
		ImageView imageView = new ImageView(new Image(getClass().getResource(backgroundImageName).toExternalForm()));
		imageView.setFitHeight(screenHeight);
		imageView.setFitWidth(screenWidth);
		return imageView;
	}

	private void initializeTimeline() {
		timeline.setCycleCount(Timeline.INDEFINITE);
		KeyFrame gameLoop = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> updateScene());
		timeline.getKeyFrames().add(gameLoop);
	}

	private void initializeBackground() {
		background.setFocusTraversable(true);
		background.setOnKeyPressed(this::handleKeyPress);
		background.setOnKeyReleased(this::handleKeyRelease);
		root.getChildren().add(background);
	}

	private void handleKeyPress(KeyEvent e) {
		KeyCode key = e.getCode();
		switch (key) {
			case UP -> user.moveUp();
			case DOWN -> user.moveDown();
			case RIGHT -> user.moveForward();
			case LEFT -> user.moveBackward();
			case SPACE -> {
				if (isSpaceEnabled) fireProjectile();
			}
			case ESCAPE -> {
				if (isESCEnabled) togglePause();
			}
			default -> {}
		}
	}

	private void handleKeyRelease(KeyEvent e) {
		KeyCode key = e.getCode();
		if (key == KeyCode.UP || key == KeyCode.DOWN) user.stopVerticalMovement();
		if (key == KeyCode.LEFT || key == KeyCode.RIGHT) user.stopHorizontalMovement();
	}

	private void fireProjectile() {
		ActiveActorDestructible projectile = user.fireProjectile();
		if (projectile != null) {
			root.getChildren().add(projectile);
			userProjectiles.add(projectile);
		}
	}

	private void updateScene() {
		spawnEnemyUnits();
		updateActors();
		generateEnemyFire();
		updateGameStatus();
		removeAllDestroyedActors();
		updateKillCount();
		updateLevelView();
		checkIfGameOver();
	}

	private void updateGameStatus() {
		handleEnemyPenetration();
		handleCollisions(userProjectiles, enemyUnits);
		handleCollisions(enemyProjectiles, friendlyUnits);
		handleCollisions(enemyProjectiles, userProjectiles);
		handleCollisions(friendlyUnits, enemyUnits);
	}

	private void generateEnemyFire() {
		enemyUnits.forEach(enemy -> {
			if (enemy instanceof FighterPlane) {
				ActiveActorDestructible projectile = ((FighterPlane) enemy).fireProjectile();
				if (projectile != null) {
					root.getChildren().add(projectile);
					enemyProjectiles.add(projectile);
				}
			}
		});
	}

	private void updateActors() {
		friendlyUnits.forEach(ActiveActorDestructible::updateActor);
		enemyUnits.forEach(ActiveActorDestructible::updateActor);
		userProjectiles.forEach(ActiveActorDestructible::updateActor);
		enemyProjectiles.forEach(ActiveActorDestructible::updateActor);
	}

	private void removeAllDestroyedActors() {
		removeDestroyedActors(friendlyUnits);
		removeDestroyedActors(enemyUnits);
		removeDestroyedActors(userProjectiles);
		removeDestroyedActors(enemyProjectiles);
	}

	private void removeDestroyedActors(List<ActiveActorDestructible> actors) {
		List<ActiveActorDestructible> destroyedActors = actors.stream().filter(ActiveActorDestructible::isDestroyed).collect(Collectors.toList());
		root.getChildren().removeAll(destroyedActors);
		actors.removeAll(destroyedActors);
	}

	private void handleCollisions(List<ActiveActorDestructible> list1, List<ActiveActorDestructible> list2) {
		for (ActiveActorDestructible actor1 : list1) {
			for (ActiveActorDestructible actor2 : list2) {
				if (actor1.getBoundsInParent().intersects(actor2.getBoundsInParent())) {
					actor1.takeDamage();
					actor2.takeDamage();
				}
			}
		}
	}

	private void handleEnemyPenetration() {
		for (ActiveActorDestructible enemy : enemyUnits) {
			if (hasPenetratedDefense(enemy)) {
				user.takeDamage();
				enemy.destroy();
			}
		}
	}

	private boolean hasPenetratedDefense(ActiveActorDestructible enemy) {
		return Math.abs(enemy.getTranslateX()) > screenWidth;
	}

	private void updateKillCount() {
		int killedEnemies = currentNumberOfEnemies - enemyUnits.size();
		for (int i = 0; i < killedEnemies; i++) {
			user.incrementKillCount();
		}
		currentNumberOfEnemies = enemyUnits.size();
	}

	private void updateLevelView() {
		levelView.removeHearts(user.getHealth());
	}

	protected void winGame() {
		disableControls();
		timeline.stop();
		levelView.showWinImage();
		showEndMenu();
	}

	protected void loseGame() {
		disableControls();
		timeline.stop();
		showEndMenu();
	}

	private void disableControls() {
		isESCEnabled = false;
		isSpaceEnabled = false;
	}

	private void showEndMenu() {
		EndMenu endMenu = new EndMenu(stage, (int) screenWidth, (int) screenHeight, controller);
		Scene endMenuScene = getRoot().getScene();
		endMenuScene.setOnKeyPressed(event -> {
			if (event.getCode() == KeyCode.ESCAPE) {
				endMenu.show();
			}
		});
		endMenu.show();
	}

	public void stopGame() {
		timeline.stop();
	}

	private void togglePause() {
		if (isPaused) {
			resumeGame();
		} else {
			pauseGame();
		}
	}

	private void pauseGame() {
		isPaused = true;
		isSpaceEnabled = false;
		timeline.pause();
		levelView.showPauseMenuImage();
		controller.pauseBackgroundMusic();
	}

	private void resumeGame() {
		isPaused = false;
		isSpaceEnabled = true;
		timeline.play();
		levelView.hidePauseMenuImage();
		if (controller.isBackgroundMusicOn()) {
			controller.resumeBackgroundMusic();
		}
	}

	// Getters for protected fields
	protected UserPlane getUser() {
		return user;
	}

	protected Group getRoot() {
		return root;
	}

	protected int getCurrentNumberOfEnemies() {
		return enemyUnits.size();
	}

	protected void addEnemyUnit(ActiveActorDestructible enemy) {
		enemyUnits.add(enemy);
		root.getChildren().add(enemy);
	}

	protected double getEnemyMaxYPosition() {
		return enemyMaxYPosition;
	}

	protected double getScreenWidth() {
		return screenWidth;
	}

	protected boolean userIsDestroyed() {
		return user.isDestroyed();
	}

	public Controller getController() {
		return controller;
	}

	public Stage getStage() {
		return stage;
	}
}
