# COMP2042 Coursework
## 👤Author
- Own Kai Jian (20611060)

## 📁Folder Structure
```
.
├── README.md
└── Design.pdf 
└── javadoc 
└── Demo.mp4
└── OwnKaiJian_IntelliJ_21.zip

```
## **Github**: Provide link to GitHub repo

## Compilation Instructions
#### Follow these steps to set up and run the project locally:
Ensure you have the correct java version and maven installed
> git clone https://github.com/kjown/COMP2042.git

Ensure you have added JavaFX library in your IDE

## Features Implemented and Working Properly
### **Player Controls**
- User navigates menus using mouse cursor and clicks on buttons to navigate through the game.
- Added horizontal movement for the player using `LEFT` and `RIGHT` arrow keys in `UserPlane` class.

### **Menus and User Interface**
- `StartMenu` implemented with `Start`, `Settings` and `Exit` buttons with `startGame()` and `showSettings()` methods for `Start` and `Settings` button, while `Exit` button exits the game. All buttons have hovering effects to improve user experience.
- `SettingsMenu` implemented with `Sound ON/OFF` toggler to turn on or off background music, `Controls` button to show the controls of the game and `Back` button to return to `StartMenu`.
- `ControlsMenu` implemented with `Back` button to return to `SettingsMenu`. `ControsMenu` shows all controls user are able to use when playing the game. 
- `PauseMenu` implemented pause functionality using `ESC` key to pause when playing the game.
- `EndMenu` implemented with `Main Menu`, `Restart` and `Exit` buttons with `showMainMenu()` and `restartGame()` methods as well as exiting the game when `Exit` is pressed. All buttons have hovering effects to improve user experience.


### **Game Mechanics**
- `Projectiles` from enemies is destroyable by user with their own respective health values.
- `Projectiles` are destroyed when Out of Bounds.
- `Boss` now has shield with its own health value and spawn probability to protect the boss.
- `Enemy2` has its own movement pattern and projectile characteristics.
- `Enemy3` has its own movement pattern and projectile characteristics.
- Enemy Spawning logic is changed to spawn enemies in a more random and efficient manner.
- Different enemy types have different movement patterns, projectile characteristics, health value and spawn logic.
- User wins and will trigger the `WinImage` when the boss is defeated in `LevelBoss`.


### **Game Flow**

### **Game Scene Management**
- **Dynamic Background**: Each level has different background image to suit the theme of the level.
- **Game End**: 
  - `WinImage` is displayed upon successful completion of the game. User can continue to `EndMenu` by pressing  `ESC` key.
  - `EndMenu` is displayed when the player loses all their lives.

### **Sound and Music Control**
- `AudioManager` class implemented to control the background music and sound effects in the game. `AudioManager` class has methods to control the music and sound effects in the game. TODO!
- Background music plays throughout the game and can be toggled on or off in the `SettingsMenu`.
- Background music stops when PauseMenu is displayed and resumes when the game is unpaused.


## **Technical Details**
- Fixed the crash that occurs in `LevelOne`.
- Fixed ShieldImage not showing up in `Boss` class.
- Fixed the hitbox of actors for more accurate and fun gameplay experience.

## Implemented but Not Working Properly

## Features not Implemented

## New Java Classes
| New Class        | Description                                                                                                                                            | Path                                                                                                                        |
|------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------|-----------------------------------------------------------------------------------------------------------------------------|
| `StartMenu.java` | The starting interface for the game. User can navigate to `SettingsMenu`, start the game or exit the game.                                             | [`com/example/demo/menu/StartMenu.java`](src/main/java/com/example/demo/menu/StartMenu.java)                                |
| `SettingsMenu.java` | The settings interface for the game. User can toggle background music on or off, view controls of the game in `ControlsMenu` or return to `StartMenu`. | [`com/example/demo/menu/SettingsMenu.java`](src/main/java/com/example/demo/menu/SettingsMenu.java)                          |
| `ControlsMenu.java` | The controls interface for the game. User can view the controls of the game and return to `SettingsMenu`.                                              | [`com/example/demo/menu/ControlsMenu.java`](src/main/java/com/example/demo/menu/ControlsMenu.java)                          |
| `PauseMenu.java` | The pause interface for the game. User can pause the game and return to the game by pressing `ESC` key.                                                | [`com/example/demo/menu/PauseMenu.java`](src/main/java/com/example/demo/menu/PauseMenu.java)                                |
| `EndMenu.java` | The end interface for the game. User can return to `StartMenu`, restart the game or exit the game.                                                     | [`com/example/demo/menu/EndMenu.java`](src/main/java/com/example/demo/menu/EndMenu.java)                                    |
| `AudioManager.java` | The audio manager for the game. User can toggle background music on or off, play sound effects and background music.                                   | [`com/example/demo/audio/AudioManager.java`](src/main/java/com/example/demo/audio/AudioManager.java)                        |
| `LevelTwo.java` | The second level of the game. User can play the second level of the game with Enemy2 of Helicopter type.                                               | [`com/example/demo/level/LevelTwo.java`](src/main/java/com/example/demo/level/LevelTwo.java)                                |
| `LevelThree.java` | The third level of the game. User can play the third level of the game with Enemy3.                                                                    | [`com/example/demo/level/LevelThree.java`](src/main/java/com/example/demo/level/LevelThree.java)                            |                                                                   
| `Helicopter.java` | The Helicopter class of the game. Used by the `Enemy2.java` class to create new enemies and deployed in `LevelTwo.java`.                               | [`com/example/demo/actors/Helicopter.java`](src/main/java/com/example/demo/actors/enemies/Helicopter.java)                  |
| `Enemy2.java` | Extends `Helicopter.java` class to specify how Enemy2 works and deploy in `LevelTwo.java`.                                                             | [`com/example/demo/actors/Enemy2.java`](src/main/java/com/example/demo/actors/Enemy2.java)                                  |
| `Enemy3.java` | Extends `FighterPlane.java` class to specify how Enemy3 works and deploy in `LevelThree.java`.                                                         | [`com/example/demo/actors/Enemy3.java`](src/main/java/com/example/demo/actors/Enemy3.java)                                  |                                                                                                                      
|`EnemyBulletProjectile.java` | Projectile type for `Enemy3`.                                                                                                                           | [`com/example/demo/actors/EnemyBulletProjectile.java`](src/main/java/com/example/demo/actors/EnemyBulletProjectile.java)    |
| `LevelViewLevelTwo.java` | Level view for `LevelTwo.java`.                                                                                                                        | [`com/example/demo/actors/levels/LevelViewLevelTwo.java`](src/main/java/com/example/demo/levels/LevelViewLevelTwo.java)     |
| `LevelViewLevelThree.java` | Level view for `LevelThree.java`.                                                                                                                      | [`com/example/demo/actors/levels/LevelViewLevelThree.java`](src/main/java/com/example/demo/levels/LevelViewLevelThree.java) |


## Modified Java Classes
### LevelParent.java
- added handleProjectileCollision() method to handle projectile collision with actors.
-  

### Boss.java
- Added `addShieldToScene()` method to add shield to the scene.
- Added `reshuffleMovePattern` to change the movement pattern of the Boss.
- Changed the logic of creating the boss projectile
- Changed `updateShieldPosition()` method to update the shield's position to match the boss's position. 
- Added `isShieldDurationExceeded()` method to check if the shield duration has exceeded the limit.
- Added  logic to check if the boss is out of vertical bounds. If out of bounds is true, resets the vertical position of the boss (not successful).

### LevelOne.java
- Added `logInitialization()` helper method to log initialization of the level.
- Added `addUserToScene()` helper method to add user to the scene.
- Added `calculateEnemiesToSpawn()` helper method 
- Added `shouldSpawnEnemy()` helper method to randomly decide if any enemy should spawn based on the probability.
- Added `generateRandomEnemyYPosition()` helper method to generate a random Y position for a new enemy.
- Added `calculateEnemiesToSpawn()` method to calculate how many enemies need to be spawned.
- 

### LevelBoss.java (previously LevelTwo.java)
- Renamed `LevelTwo.java` to `LevelBoss.java`.

### LevelViewLevelBoss.java (previously LevelViewLevelTwo.java)
- Renamed `LevelViewLevelTwo.java` to `LevelViewLevelBoss.java`.
- 

### Projectile.java
- Added `health` variable to store the health value of the projectile. 
- If `healthAtZero()` is true, the projectile is destroyed.
- `Projectile` is destroyable by user.



### EnemyProjectile.java
- Added `health` variable to store the health value of the projectile. 
- `EnemyProjectile` is destroyable by user.

### BossProjectile.java
- made `BossProjectile` to be destroyable by user by adding health value.

### EnemyRocketProjectile.java (previously EnemyProjectile.java)
- Renamed `EnemyProjectile.java` to `EnemyRocketProjectile.java`.

### Enemy1.java (previously EnemyPlane.java)
- Renamed `EnemyPlane.java` to `Enemy1.java`.


## Unexpected Problems
