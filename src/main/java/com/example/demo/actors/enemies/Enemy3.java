package com.example.demo.actors.enemies;

import com.example.demo.actors.ActiveActorDestructible;
import com.example.demo.actors.FighterPlane;
import com.example.demo.actors.projectiles.EnemyBulletProjectile;
import com.example.demo.view.LevelViewLevelThree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Enemy3 extends FighterPlane {
    private static final String IMAGE_NAME = "enemyplane3.png";
    private static final double INITIAL_X_POSITION = 1000.0;
    private static final double INITIAL_Y_POSITION = 400;
    private static final int IMAGE_HEIGHT = 50;
    private static final int VERTICAL_VELOCITY = 6;
    private static final int HORIZONTAL_VELOCITY = 6;
    private static final int INITIAL_HEALTH = 3;
    private static final double FIRE_RATE = .03;
    private static final int MOVE_FREQUENCY_PER_CYCLE = 5;
    private static final int ZERO = 0;
    private static final int Y_POSITION_UPPER_BOUND = -100;
    private static final int Y_POSITION_LOWER_BOUND = 475;
    private static final int X_POSITION_LEFT_BOUND = -50; // New left boundary
    private static final int X_POSITION_RIGHT_BOUND = 1050;
    private int indexOfCurrentMove;
    private static final double PROJECTILE_Y_POSITION_OFFSET = 75.0;
    private static final double PROJECTILE_X_POSITION_OFFSET = -100.0;

    private final List<int[]> movePattern;
    private int consecutiveMovesInSameDirection;
    private static final int MAX_FRAMES_WITH_SAME_MOVE = 10;
    private LevelViewLevelThree levelView;



    public Enemy3(LevelViewLevelThree levelView) {
        super(IMAGE_NAME, IMAGE_HEIGHT, INITIAL_X_POSITION, INITIAL_Y_POSITION, INITIAL_HEALTH);
        movePattern = new ArrayList<int[]>();
        consecutiveMovesInSameDirection = 0;
        indexOfCurrentMove = 0;
        this.levelView = levelView;
        initializeMovePattern();
    }

    @Override
    public void updatePosition() {
        double initialTranslateX = getTranslateX();
        double initialTranslateY = getTranslateY();

        int[] nextMove = getNextMove();
        moveHorizontally(nextMove[0]);
        moveVertically(nextMove[1]);

        double currentXPosition = getLayoutX() + getTranslateX();
        double currentYPosition = getLayoutY() + getTranslateY();

        // Ensure the enemy stays within the boundaries
        if (currentYPosition < Y_POSITION_UPPER_BOUND || currentYPosition > Y_POSITION_LOWER_BOUND) {
            setTranslateY(initialTranslateY);
        }

        if (currentXPosition < X_POSITION_LEFT_BOUND || currentXPosition > X_POSITION_RIGHT_BOUND) {
            setTranslateX(initialTranslateX);
        }
    }

    @Override
    public void updateActor() {
        updatePosition();
    }

    @Override
    public ActiveActorDestructible fireProjectile() {
        if (Math.random() < FIRE_RATE) {
            double projectileXPosition = getProjectileXPosition(PROJECTILE_X_POSITION_OFFSET);
            double projectileYPostion = getProjectileYPosition(PROJECTILE_Y_POSITION_OFFSET);
            return new EnemyBulletProjectile(projectileXPosition, projectileYPostion);
        }
        return null;
    }

    private void initializeMovePattern() {
        for (int i = 0; i < MOVE_FREQUENCY_PER_CYCLE; i++) {
            // Each move is a (dx, dy) pair
            movePattern.add(new int[]{HORIZONTAL_VELOCITY, VERTICAL_VELOCITY});  // Move diagonally down-right
            movePattern.add(new int[]{-HORIZONTAL_VELOCITY, -VERTICAL_VELOCITY}); // Move diagonally up-left
            movePattern.add(new int[]{HORIZONTAL_VELOCITY, ZERO});               // Move right
            movePattern.add(new int[]{-HORIZONTAL_VELOCITY, ZERO});              // Move left
            movePattern.add(new int[]{ZERO, VERTICAL_VELOCITY});                 // Move down
            movePattern.add(new int[]{ZERO, -VERTICAL_VELOCITY});                // Move up
        }
        Collections.shuffle(movePattern);
    }

    private int[] getNextMove() {
        int[] currentMove = movePattern.get(indexOfCurrentMove);
        consecutiveMovesInSameDirection++;
        if (consecutiveMovesInSameDirection == MAX_FRAMES_WITH_SAME_MOVE) {
            Collections.shuffle(movePattern);
            consecutiveMovesInSameDirection = 0;
            indexOfCurrentMove++;
        }
        if (indexOfCurrentMove == movePattern.size()) {
            indexOfCurrentMove = 0;
        }
        return currentMove;
    }
}
