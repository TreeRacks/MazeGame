package com.mycompany.foodsearchingforwinter;

import java.awt.*;
import java.awt.image.*;
import java.text.DecimalFormat;

import Title.MainScreen;
import Title.Help;

import StaticObject.Reward_carrot;
import StaticObject.Reward_medkit;

/**The user interface class for display game information on screen. */
public class UI {
    GamePanel gp;
    Graphics2D g2;
    Font arial_40, arial_80B;
    public BufferedImage carrotImage;
    public BufferedImage medkitImage;
    public BufferedImage mainScreenImage;
    public BufferedImage helpScreenImage;
    /**for checking if need to display image */
    public boolean messageOn = false;
    /**the string you want to display on screen */
    public String message = "";
    public int messageCounter = 0;
    /**check if game needs to finish */
    public boolean gameFinished = false; //if player get all carrot
    /**check if player losses the game */
    public boolean gameLoss = false; //if player have negative score

    public int commandNum = 0;

    public int x,y;

    //timer for game
    double play_time;
    DecimalFormat dformat = new DecimalFormat("#0.00");

    /**
     * Setting the font for messages display on screen.
     * Reads all reward images
     * draw the in-game interface, not the menu
     * @param gp need GamePanel for drawing stuff
     */
    public UI(GamePanel gp){
        this.gp = gp;
        arial_40 = new Font("Arial", Font.PLAIN, 40);
        arial_80B = new Font("Arial", Font.BOLD, 80);

        /*Drawing in game carrot counter*/
        Reward_carrot carrot = new Reward_carrot();
        carrotImage = carrot.image;
        /*Drawing in game medkit counter*/
        Reward_medkit medkit = new Reward_medkit();
        medkitImage = medkit.image;
        MainScreen mainScreen = new MainScreen();
        mainScreenImage = mainScreen.image;
        Help helpScreen = new Help();
        helpScreenImage = helpScreen.image;
        
    }
    /**
     * This method gets the text you want to display
     * @param text string for displaying in the screen
     */
    public void showMessage(String text){
        message = text;
        messageOn = true;
    }


    /*the name of this method is kind of confusing,
    * there is a draw() method in the Bunny class and it would be better if we can differentiate them
    * Better call this drawGameStatus()*/
    /**This method will show the score and carrot for bunny in
     * the screen, and will print message when bunny collect something
     * @param g2 the image of screen
     */
    public void drawGameStatus(Graphics2D g2) {
        this.g2 = g2;
        setFontAndColor(arial_40, Color.white);
        //TITLE SCREEN
        if(gp.gameState == gp.titleState){
            drawTitle();
        }
        if(gp.gameState == gp.playState){
            if(gameFinished == true && gameLoss==false){
                drawGameFinishScreen();
            }
            else if (gameLoss == true){
                drawGameLoseScreen();
            }
            else{
                drawGameProcessScreen();
            }
        }
        if(gp.gameState == gp.pauseState){
            pauseScreen();
        }
        if(gp.gameState == gp.endState){
            drawGameOverScreen();
        }
    }

    public void setFontAndColor(Font font, Color color){
        g2.setFont(font);
        g2.setColor(color);
    }

    /**This method is draw screen for user finishing the game
     * after collect all carrots
     */
    public void drawGameFinishScreen(){
        setFontAndColor(arial_80B, Color.WHITE);
        String text;
        int textLength;

        text = "Congratulation! You win!";
        textLength =(int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();

        x = gp.screenWidth/2 - textLength/2;
        y = gp.screenHeight/2;

        g2.drawString(text, x, y);

        gp.gameThread = null;
    }
        /**This method is draw screen that users meet the wolves or
         * the score below 0
        */
    public void drawGameLoseScreen(){
        setFontAndColor(arial_80B, Color.WHITE);

        String text;
        int textLength;

        text = "oh no! You lost!";
        textLength =(int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();

        x = gp.screenWidth/2 - textLength/2;
        y = gp.screenHeight/2;

        g2.drawString(text, x, y);
        gp.gameState = gp.endState;
    }
    /**This method will draw the some basic information like score, processing time 
     * number of carrots and medkit for users
     */
    public void drawGameProcessScreen(){
        setFontAndColor(arial_40, Color.WHITE);
        //LOAD SCORE OF BUNNY
        g2.drawString("Score: " + gp.bunny.score, 25, 50);

        //LOAD CARROT INFORMATION HERE
        g2.drawImage(carrotImage, 5*gp.tileSize,12, 48, 48, null);
        g2.drawString( " : " + gp.bunny.carrotNum + "/5", 268 , 50);

        //ADD MEDKIT SESSION HERE
        g2.drawImage(medkitImage, 400,12, 48, 48, null );
        g2.drawString( " x " + gp.bunny.medkitNum, 430 , 50);

        //calculate the time
        play_time+= (double)1/60; //60 frame/sec so 
        g2.drawString("Total time: "+dformat.format(play_time)+" sec", 600, 50);

        if(messageOn == true){
            g2.setFont(g2.getFont().deriveFont(30F));
            g2.drawString(message, gp.tileSize*9,gp.tileSize*13);
            messageCounter++;

            //The message will print 2 seconds in the screen, cuz FPS = 60
            if(messageCounter > 120){
                messageCounter = 0;
                messageOn = false;
            }
        }
    }
    /**This method will draw the Game over Screen when bunny die
     * and see two option "Retry" and "Quit"
     */
    public void drawGameOverScreen(){
        g2.setColor(new Color(0,0,0,150));
        g2.fillRect(0,0,gp.screenWidth,gp.screenHeight);

        String text;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 110F));

        text = "GAME OVER";

        //SHADOW
        g2.setColor(Color.black);
        x = getCentreX(text);
        y = gp.tileSize*4;
        g2.drawString(text,x,y);

        //MAIN
        g2.setColor(Color.white);
        g2.drawString(text, x -4, y-4);

        //TWO OPTION
        //RETRY
        g2.setFont(g2.getFont().deriveFont(50F));
        text = "Retry";
        x = getCentreX(text);
        y += gp.tileSize*4;
        g2.drawString(text,x,y);
        if(commandNum == 0){
            g2.drawString(">",x-40,y);
        }

        //BACK TO TITLE SCREEN
        text = "Quit";
        x = getCentreX(text);
        y+=48;
        g2.drawString(text,x,y);
        if(commandNum == 1){
            g2.drawString(">",x-40,y);
        }
    }

    public void drawBunnyOnTitle(){
        x = gp.screenWidth/2 - 1*gp.tileSize;
        y += gp.tileSize*2;
        g2.drawImage(gp.bunny.down1, x, y, gp.tileSize*2, gp.tileSize*2, null);
    }

    public void drawMenuOptions(){
        String text = "Food Searching For Winter";
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 48F));

        //NEW GAME option
        g2.setColor(Color.RED);
        text = "NEW GAME";
        x = getCentreX(text);
        y += gp.tileSize*4;
        g2.drawString(text, x, y);
        if(commandNum == 0){
            g2.drawString(">>", x-gp.tileSize*2,y);
        }

        //HELP option
        g2.setColor(Color.yellow);
        text = "HELP";
        x = getCentreX(text);
        y += gp.tileSize*2;
        g2.drawString(text, x, y);
        if(commandNum == 1){
            g2.drawString(">>", x-gp.tileSize*2,y);
            g2.drawImage(helpScreenImage, 850, 350, gp.tileSize*8, gp.tileSize*8, null); // draw the help window image
        }

        //EXIT GAME option
        g2.setColor(Color.gray);
        text = "EXIT GAME";
        x = getCentreX(text);
        y += gp.tileSize*2;
        g2.drawString(text, x, y);
        if(commandNum == 2){
            g2.drawString(">>", x-gp.tileSize*2,y);
        }
    }
    /**draw the title screen */
    public void drawTitle(){
        //TITLE SCREEN
        g2.drawImage(mainScreenImage, 0, 0, null);

        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 96F));
        String text = "Food Searching For Winter";
        x = getCentreX(text);
        y = gp.tileSize*3;

        //SHADOW
        g2.setColor(Color.GRAY);
        g2.drawString(text, x+5, y+5);

        //TITLE
        g2.setColor(Color.WHITE);
        g2.drawString(text,x,y);

        //Bunny IMAGE
        drawBunnyOnTitle();

        //Display menu
        drawMenuOptions();

    }
    /**make the game pause */
    public void pauseScreen(){
        this.g2 = g2;
        g2.setFont(arial_80B);
        String text = "PAUSED";
        x = getCentreX(text);
        y = gp.screenHeight /2;

        g2.drawString(text, x, y);
    }
    public int getCentreX(String text){
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth / 2 - length /2;
        return x;
    }
}
