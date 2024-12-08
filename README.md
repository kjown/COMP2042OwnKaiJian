# COMP2042 Coursework
## 👤Author
Own Kai Jian 20611060

[Github Repository](https://github.com/kjown/COMP2042.git)

## 📁Folder Structure
```
.
├── README.md
└── Design.pdf 
└── javadoc 
└── Demo.mp4
└── OwnKaiJian_IntelliJ_21.zip
```

## Compilation Instructions
### **Prerequisites**
#### Follow these steps to set up and run the project locally:
Ensure you have the correct java version and maven installed:
1. Maven (v3.9 or above)
2. Java (JDK 19 or above)

You can verify this by executing the following command

```shell
mvn --version
java --version
```
### **Running**
Run the following command to run the game
```shell
./mvnw clean javafx:run
```


## Features Implemented and Working Properly
### **Player Controls**
- User navigates menus using mouse cursor and clicks on buttons to navigate through the game.
- Added horizontal movement for the player using `LEFT` and `RIGHT` arrow keys in `UserPlane` class.
- Pause the game by pressing `ESC` key.

### **Menus and User Interface**
#### Visual Design
- The menus and UI elements follow a cohesive design language with consistent fonts, colours and button styles.
- Buttons and labels are easy to read and navigate ensuring accessibility for all users.
- Buttons have hover effects to provide feedback to the user.

#### Start Menu
- Provides entry point options for the user to start the game, view settings or exit the game.
- `StartMenu` implemented with `Start`, `Settings` and `Exit` buttons with `startGame()` and `showSettings()` methods for `Start` and `Settings` button, while `Exit` button exits the game. All buttons have hovering effects to improve user experience.
- Utilised helper methods to handle creation of UI elements like `createTitleText` and `createStyledButton`.
- Centralised font loading logic with `loadFont` method.
- Lambda expressions for simplified button action assignments.
- Has hover sound effects for buttons.

#### Settings Menu
- Offers customisation options for the user's experience.
- `SettingsMenu` implemented with `Sound ON/OFF` toggler to turn on or off background music, `Controls` button to show the controls of the game and `Back` button to return to `StartMenu`.
- Used lambdas for cleaner action assignments.
- Has hover sound effects for buttons.

#### Controls Menu
- `ControlsMenu` implemented with `Back` button to return to `SettingsMenu`. `ControsMenu` shows all controls user are able to use when playing the game. 
- Encapsulated logic for creating the layout and adding elements like images and instructions.
- Has hover sound effects for buttons.

#### Pause Menu
- `PauseMenu` implemented pause functionality using `ESC` key to pause when playing the game.

#### End Menu
- Displays when the user loses all their lives.
- `EndMenu` implemented with `Main Menu`, `Restart` and `Exit` buttons with `showMainMenu()` and `restartGame()` methods as well as exiting the game when `Exit` is pressed. All buttons have hovering effects to improve user experience.
- Encapsulated the game restart and main menu navigation logic in separate methods for clarity.
- Has hover sound effects for buttons.


### **Game Flow**
#### Core Gameplay Loop
The game revolves around a series of levels where the user controls a fighter plane. The primary goal is to survive and destroy the enemies. The game follows these steps:
There are a total of 5 playable levels. 

#### Start Game Cutscene
- A cutscene is displayed to introduce the game to the player.
- User can continue to `LevelOne` by pressing `SPACE` key.

#### Level One
- The user begins in control of a `UserPlane` actor. The plane is positioned at the left side of the screen.
- `LevelOne` implemented with `UserPlane` and `Enemy1` actors. User can play the first level of the game with Enemy1.
- `Enemy1` are spawned in random positions and move in a straight line.
- The user can fire projectiles at incoming enemies using `SPACEBAR`.
- The user has 5 lives and loses a life when hit by an enemy projectile. If the hearts reaches zero, the user die and will proceed to `EndMenu`.
- When user destroys 10 enemies, the user will proceed to `LevelTwo`.

#### Level Two
- `LevelTwo` implemented with `UserPlane` and `Enemy2` actors. User can play the second level of the game with Enemy2 of Helicopter type.
- `Enemy2` are spawned in random positions.
- User has to destroy all enemies to proceed to the next level.
- Has a desert theme.

#### Level Three
- `LevelThree` implemented with `UserPlane` and `Enemy3` actors. User can play the third level of the game with Enemy3.
- `Enemy3` are spawned in random positions with random movement sets to simulate fast moving kamikaze planes.
- User has to destroy 10 enemies to proceed to the next level. 
- Has a sunset theme.

#### Level Four
- `LevelFour` implemented with `UserPlane` and `Enemy1` actors. User can play the fourth level of the game with Enemy4 and Enemy5.
- The level will have fast moving `Enemy4` attacking user and protecting `Enemy5`.
- User has to destroy `Enemy5` to proceed to the next level.
- Has a futuristic snowy tundra space station theme.

#### Level 5 (Level Boss)
- This is the last level of the game.
- `LevelBoss` implemented with `UserPlane` and `Boss` actors. User can play the final level of the game with the Boss.
- User wins the game by defeating the `Boss`, who has a large amount of health and a protective shield.
- User has to destroy the shield before destroying the `Boss`.
- Has an ocean theme.

#### Win Image
- `WinImage` is displayed upon successful completion of the game. User can continue to `EndMenu` by pressing  `ESC` key.

#### End Menu
- `EndMenu` is displayed when the player loses all their lives. User can return to `StartMenu`, restart the game or exit the game.

### **Game Mechanics**
#### UserPlane
- User can move horizontally and vertically.
- User fires `UserProjectile` when `SPACEBAR` is pressed.
- User will have a shield that activates whenever user destroys 2 enemies.
- User will turn red when hit by enemy projectiles.
- User has 5 lives and loses a life when hit by an enemy projectile. If the hearts reaches zero, the user die and will proceed to `EndMenu`.

4 new Enemy types are implemented in the game, each with different characteristics.
#### Enemy 1
- `Enemy1` has its own movement pattern and projectile characteristics.
- Has 1 health.
- Fires `EnemyRocketProjectile` projectiles.
- Spawns at random positions.

#### Enemy 2
- `Enemy2` has its own movement pattern and projectile characteristics.
- Actor of `Helicopter` type.
- Moves at slower speed than `Enemy1`.
- Has more health. Able to take more damage.
- Fires `EnemyBulletProjectile` projectiles at a higher fire rate.

#### Enemy 3
- `Enemy3` has its own movement pattern and projectile characteristics.
- Moves at random with a higher speed. 
- Has a Japanese Kamikaze plane theme.
- Fires `EnemyBulletProjectile`.
- Has more health. Able to take more damage.

#### Enemy 4
- `Enemy4` has its own movement pattern and projectile characteristics.
- Moves at a slower speed.
- Has more health. Able to take more damage.
- Fires `LaserProjectile` projectiles.
- Functions as a mini boss type in Level4.
- Has a sci-fi theme.

#### Enemy 5
- `Enemy5` has its own movement pattern and projectile characteristics.
- Moves at a faster speed.
- Has 3 health.
- Fires `LaserProjectile` projectiles.
- Spawns at random positions.
- Has a sci-fi theme.

#### Boss
- `Boss` now has shield with its own health value and spawn probability to protect the boss.
- `Boss` now displays its own `HealthBar` to show the health of the boss.
- `Boss` has its own movement pattern and projectile characteristics.
- `Boss` has large amount of health.
- Fires `BossProjectile` projectiles.

#### Projectiles
- `Projectiles` are fired by user and enemies.
- `Projectiles` from enemies is now destroyable by user with their own respective health values.
- `Projectiles` are destroyed when Out of Bounds.

##### UserProjectile
- Fired by the user's plane when the `SPACEBAR` is pressed.
- Moves in a straight line towards the top of the screen.
- Can destroy enemy actors.

###### EnemyBulletProjectile
- Fired by `Enemy2` and `Enemy3`.
- Has a higher fire rate compared to EnemyRocketProjectile.
- Can be destroyed by the user's projectiles.

###### EnemyRocketProjectile
- Fired by `Enemy1`.
- Moves in a straight line.

###### LaserProjectile
- Fired by `Enemy4` and `Enemy5`.
- Has a futuristic laser theme.

###### BossProjectile
- Fired by `Boss`.

### **Game Scene Management**
- **Dynamic Background**: Each level has different background image to suit the theme of the level.
- **Game End**: 
  - `WinImage` is displayed upon successful completion of the game. User can continue to `EndMenu` by pressing  `ESC` key.
  - `EndMenu` is displayed when the player loses all their lives.

### **Sound and Music Control**
- `AudioManager` class implemented to control the background music and sound effects in the game. `AudioManager` class has methods to control the music and sound effects in the game.
-  `Singleton` pattern is used to ensure only one instance of the `AudioManager` class is created.
- Background music plays throughout the game and can be toggled on or off in the `SettingsMenu`.
- Background music stops when PauseMenu is displayed and resumes when the game is unpaused.
- `AudioManager` class has methods to play sound effects.
- When an actor is destroyed, there will be explosion sound effects.
- When user fires projectile, there will be a shooting sound effect.

### **Visual Effects**
- **Explosions**: Explosions are displayed when an enemy is destroyed.
- **Shield**: User will have a shield that activates whenever user destroys 2 enemies.
- **Health Bar**: `Boss` now displays its own `HealthBar` to show the health of the boss.
- When user takes damage, the user will experience a screen shake.

### **Technical Details**
- Fixed the crash that occurs in `LevelOne`.
- Fixed ShieldImage not showing up in `Boss` class.
- Fixed the hitbox of actors for more accurate and fun gameplay experience.

## Implemented but Not Working Properly
### Screen Shake
- Implemented screen shake when user takes damage but not working properly.
- Screen shake may cause user to see white space.

### Toggling Sound On/Off in Settings Menu
- A bug where toggling off, then on again will cause the background music to play unexpectedly.

## Features not Implemented
-  `UserPlane` turning red when it takes damage.
- `Power Ups` not implemented.
- `High Score` not implemented.
- `Special Movements` not implemented. 
- Smoother frame rates is not implemented.
- 

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
### Controller.java
- Getter and setter methods for sound effect related methods such as `isBackgroundMusicOn`, `setBackgroundMusicOn`, `pauseBackgroundMusic`, `playBackgroundMusic` and `resumeBackgroundMusic` to provide controlled access to the `AudioManager` class.
- Encapsulated level transition logic in `goToLevel`. 
- Smaller, more focused methods for better readability and maintainability. For example, the `launchGame` method is focused on starting the game, leaving the background music initialization to its own method.
- `Observer pattern` utilized to manage level transitions, reducing coupling between controller and level classes.
- `Reflection` was used for dynamic instantiation of levels, making it easier to scale the game with minimal changes.
- Centralized error handling with dedicated `showAlert` method, which is used in both `launchGame` and `goToLevel` methods. This reduces duplication and improves maintainability of the code.
- Whenever error occurs, the exception is logged with e.printStackTrace() and a user-friendly alert is shown with the exception details to make debugging easier.

### LevelParent.java
- added handleProjectileCollision() method to handle projectile collision with actors.

### Enemy1.java

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
