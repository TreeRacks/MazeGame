### A Very Brief Maze Game
This application was developed from scratch entirely in Java and built with Maven. The development process took approximately a week or two from start to finish. 

### Game Description 

The objective of the game is to obtain all the carrots on the map and make it to the exit with as high of a score as possible. The user can obtain optional medical kits and avoid eating the poisonous carrots to maximize their score. The game displays the player's score and information about the rewards received as well as the time of the game at the top. Players can use the keyboard **W, S, A, D** as **up, down, left, right** direction, and **press P to pause the game**. Players can restart the game if they happen to lose, which occurs when the player's score goes below zero or they encounter a wolf.

### Development Summary
1. We start from a tile manager class and some directional images of the bunny sprite. The tile manager will read an image and form a map for us, which will then act as the maze the player will interact with. ([Tile.java](foodSearchingForWinter\src\main\java\Tile\Tile.java) & [TileManager.java](foodSearchingForWinter\src\main\java\Tile\TileManager.java))
2. Afterwards we then began work on implementing ``Bunny.java`` and ``GamePanel.java``. At this point, ``Bunny.java`` only contains basic functions such as ``draw()`` and ``readimage()``. ([Bunny.java](foodSearchingForWinter\src\main\java\Objects\Bunny.java) & [GamePanel.java](foodSearchingForWinter\src\main\java\com\mycompany\foodsearchingforwinter\GamePanel.java))
3. Then, we need to judge which map elements should have collision with the player object. A trivial example would be the grass walls, which are part of the maze structure. To prevent the player object from phasing into or through the maze, these elements must have collision implemented. ([CollisionCheck.java](foodSearchingForWinter\src\main\java\com\mycompany\foodsearchingforwinter\CollisonCheck.java))
4. After we implemented a basic collision check for the player object into GamePanel, in addition to creating basic keyboard controls for players to control the bunny, which allows the user to interact with the environment and carry out objectives. ([Control.java](foodSearchingForWinter\src\main\java\com\mycompany\foodsearchingforwinter\Control.java))
5. We create ``PlaceSetter`` to set the position of all rewards for our game and a ``pickupRewards`` method to handle logic for the reward items.([PlaceSetter.java](foodSearchingForWinter\src\main\java\com\mycompany\foodsearchingforwinter\PlaceSetter.java))
6. We then add UI class to display message for game and gamestate switching. It will change ``PLAYSTATE``, ``TITLESTATE``, ``PAUSESTATE`` and ``ENDSTATE`` for the game.  Key controls are also implemented for each ``STATE``([UI.java](foodSearchingForWinter\src\main\java\com\mycompany\foodsearchingforwinter\UI.java))
7. We implement A* algorithm for wolf pathfinding, which causes the wolf to take the shortest path to reach the bunny, but we set the speed of wolf slower than the player in order to make the game easier. ([Wolf.java](foodSearchingForWinter\src\main\java\Objects\Wolf.java) & [Pathfinding](foodSearchingForWinter\src\main\java\Pathfinding\PathFinder.java))
8. We update GamePanel continously as well as the rest of the class because there are always some components we need to add to other classes thereby increasing the coupling of our project.


### Run
A brief gameplay can be found [here](https://drive.google.com/file/d/1_cEIcMWPRDuTTcdzCg5cIWwhVOY-38oG/view?usp=sharing). 

If the [main function](foodSearchingForWinter\src\main\java\com\mycompany\foodsearchingforwinter\Main.java) is built and ran, a pop-up window will appear to start the game. Select **"new game"** to start the game, select **"help"** to view the rules of the game, and select **"quit"** to exit the game. The GamePanel class will create a gameThread for the user, starting an instance of the application in a separate window.


### Testing

The game is tested by Maven-based JUnit for each class method. Our tests are located at 'foodSearchingForWinter\src\test\java'. All [unit tests](foodSearchingForWinter\src\test) can be found there.

### JavaDoc
Our JavaDoc is [here](foodSearchingForWinter\target\foodSearchingForWinter-1.0-javadoc.jar) It has all methods and class descriptions for our game.
The Java documentation is inside the target folder. The file allclasses-index.html contains all documentation regarding our implementation.

### Team Members
Team members can be reached at their following contacts:

Clinton: ``cwa230@sfu.ca``

Jackson: ``yya216@sfu.ca``

Joanna: ``liyuyul@sfu.ca``

Linda ``mml31@sfu.ca``
