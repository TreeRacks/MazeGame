# MazeGame
A brief maze game in which a rabbit must avoid being eaten while collecting all the appropriate food items.

Game Description
The game is a game where the player, as bunny, gets all the carrots on the map as well as the optional medical kit to avoid all the wolves and avoid eating the poisonous carrots and eventually get to the exit. The game displays the player's score and information about the rewards received as well as the time of the game at the top. Players can use the keyboard W, S, A, D as up, down, left, right direction, and press P to pause the game. Players can restart the game with the option of failing the game.


Build

We start from tile manager and some directional image of bunny. Tile Manager will read image and form a map for us. (Tile.java & TileManager.java)
Then Bunny.java and GamePanel.java. Bunny has basic function such as draw() and read image at first. GamePanel.java we just put anything on it so far. (Bunny.java & GamePanel.java)
Then, we need to judge which element should have collision with bunny. - Grasswall is collision with bunny.(CollisionCheck.java)
After we add check collision into GamePanel, we create key control for users to control bunny, which is the interact from user with bunny.(Control.java)
We create PlaceSetter to set all rewards for our game and also we add pickupRewards method for bunny(PlaceSetter.java)
We add UI class to display message for game and swith game state too. It will change PLAY STATE, TITLE STATE, PAUSE STATE and END STATE for game. Also we give timer and player score for it. And updated key control for each STATE(UI.java)
We implement A* algorithm for wolf pathfinding, which make wolf use the faster time to catch bunny, but we set the speed of wolf slower than bunny which make the game easier.(Wolf.java & Pathfinding)
We update GamePanel continously and rest of class because there are always some components we need to add to other class. It increases coupling of our project.


Run
Our Introduction of the Game Video is here! You can have a look!
It only take no more one minutes! Thank you for watching!
If you run the main function of the game, you will get a pop-up window to start the game, select "new game" to start the game, select "help" to view the rules of the game, and select "quit" to exit the game. The GamePanel class have a method run and it will call game thread for our game, therefore, it will run our game.

Test
The game is tested by Maven-based JUnit for each class method. Our tests are located at 'foodSearchingForWinter\src\test\java'. You can find all of our unit tests there. Just compile it, and it will pass all test.

JavaDoc
Our JavaDoc is here It has all method and class description for our game.
The Java documentation is inside the target foulder, click on allclasses-index.html will pop a website contain all documentations
