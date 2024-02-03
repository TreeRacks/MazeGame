package com.mycompany.foodsearchingforwinter;

import Objects.Bunny;
import Objects.Wolf;
import Pathfinding.PathFinder;
import StaticObject.All_Reward;
import Tile.TileManager;

import javax.swing.*;
import java.awt.*;

/**
 * this GamePanel will have all the functions in JPanel
 */
public class GamePanel extends JPanel implements  Runnable{
    //game screen setting
    final int fixedTileSize = 16;
    final int scaleFactor = 3;

    /**each tile width or hight =48 */
    public int tileSize = fixedTileSize * scaleFactor; //48
    /**bunny tile width */
    public int bunnytileWidth = 16 * scaleFactor; // 48
    /**bunny tile height */
    public int bunnytileHeight = 20 * scaleFactor; //60

    /** wolf tile size */
    public int wolftileWidth = 16 * scaleFactor; // 48
    /** wolf tile size */
    public int WolftileHeight = 16 * scaleFactor; //48

    /**the window UI col */
    public final int maxScreenCol = 27; //origin 16
    /**the window UI row */
    public final int maxScreenRow = 17; //origin 12
    /**total # of pixel for window width */
    public final int screenWidth = maxScreenCol * tileSize; //16X48
    /**total # of pixel for window height */
    public final int screenHeight = maxScreenRow * tileSize;

    utilityTool utool = new utilityTool(this);

    //add the control to the gamepanel
    Control keyControl = new Control(this);

    //set up FPS
    int FPS = 60;
    /**manage placement of tiles on map */
    public TileManager tileM = new TileManager(this);
    Thread gameThread;
    /**check collision*/
    public CollisonCheck checker = new CollisonCheck(this); //change
    /**set rewards or other thing*/
    public PlaceSetter theSetter = new PlaceSetter(this);

    /**User Interface*/
    public UI ui = new UI(this);
    /**adding the bunny player*/
    public Bunny bunny = new Bunny(this, keyControl);
    /**array of object belong rewards class */
    public All_Reward the_rewards[] = new All_Reward[20];


    /** number of wollf **/
    public Wolf wolf[] = new Wolf[3];

    /** pathfinding engine */
    public PathFinder Path = new PathFinder(this);


 
    /**Game state number */
    public int gameState;
    /**number for titlestate */
    public final int titleState = 0;
    /**number for playState */
    public final int playState = 1;
    /**number for pauseState */
    public final int pauseState = 2;
    /**number for endState */
    public final int endState = 4;
    
    


    /**constructor for setting the backgroup color, dimention, key-controls of gamePanel */
    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyControl);
        this.setFocusable(true);
    }
    /**This method sets up the position of rewards and door in the map
     */
    public void setUpGame(){
        theSetter.setThing();
        theSetter.setWolf();
        gameState = titleState;
    }
    /**
     * This method passes the game panel into the Thread()
     */
    public void startGameThread(){
        //passing the game panel into the Thread()
        //this = the gamepanel in this case
        gameThread = new Thread(this);
        //automatically call the override method
        gameThread.start();
    }

    //automatically call this

    /**This method is for the animation of the screen, because the refresh rate of the screen is too fast. We use a game
     * loop to slow it down and for each update it will coming with a repaint to make the screen coherent
     * FPS is 60, currentTime is bigger than lastTime and currentTime - lastTime = time passed, then update lastTime
     */
    @Override
    public void run() {
        //time for update the frames
        double drawInterval = 1000000000/FPS;//refresh rate of the update
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        //create the game loop
        while (gameThread!= null){
            currentTime = System.nanoTime();
            //how much time has past
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;
            //this loop should keep things updated
            //keep drawing the screen according to the new info
            //update and repaint
            if (delta >= 1){
                update();
                repaint();
                delta--;
            }

        }
    }

    /**This method will update the position in the map of the character(bunny), the detail implementation
     *  in Objects.bunny
     */
    public void update(){
        if(gameState == playState){
            //PLAY STATE
            bunny.update();
            /** update for wolf */
            for (int i = 0; i < wolf.length; i++){
                wolf[i].update();
            }
        }
        if(gameState == pauseState){
            //PAUSE STATE
        }
    }

    /**This method will draw the game component
     * @param g Graphics object used to draw images
     */
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        //cascating the g to Graphics2D type
        Graphics2D g2 = (Graphics2D) g;

        //TITLE SCREEN
        if(gameState == titleState){
            ui.drawGameStatus(g2);
        }
        else{      
            tileM.drawMap(g2);
            //draw rewards
            for(int i = 0; i<the_rewards.length;i++){
                if(the_rewards[i]!= null){
                    the_rewards[i].draw(g2, this);
                }
            }

            /** draw bunny */
            bunny.draw(g2);

            /** draw wolf */
            for (int i = 0; i < wolf.length; i++){
                if (wolf[i] != null){
                    wolf[i].draw(g2, this);
                }
            }


    
            ui.drawGameStatus(g2);
            g2.dispose();}
  
    }
    /**This method will restart the game for game over state
     */
    public void restart(){
        
        bunny.score = 0;
        bunny.setBunnyBasic();
        theSetter.setThing();
        theSetter.setWolf();
        
    }
}