package Objects;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;

import javax.imageio.ImageIO;

import com.mycompany.foodsearchingforwinter.GamePanel;
import Objects.Object;

public class Wolf extends Object{
    String name;
    int actionLockCounter = 0;
    int xpo;
    int ypo;
    public Wolf(GamePanel gp){
        super(gp);
        name = "Wolf";
        xpo = worldX;
        ypo = worldY;
        solidArea = new Rectangle(8, 3, 32, 30);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        getWolfImage();
        setWolfBasic();
    }
    /**This class will set some basic attributes of wolf
     * speed and direction
     */
    public void setWolfBasic(){
        speed = 1;
        onPath = true;
        direction = "down";
    }

    /**
     * This method is the pixel image getter for the wolf
     */
    public void getWolfImage() {
        try {
            up1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Wolf/up1.png")));
            up2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Wolf/up2.png")));
            down1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Wolf/down1.png")));
            down2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Wolf/down2.png")));
            right1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Wolf/right1.png")));
            right2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Wolf/right2.png")));
            left1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Wolf/left1.png")));
            left2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Wolf/left2.png")));
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    /**
     * This method will set action of wolf up to the distance, and it will
     * get random number to set its direction
     */
    public void setAction(){
        if (onPath == true){

            int goalCol = (gp.bunny.worldX)/gp.tileSize;
             // System.out.println("goal col = " + goalCol);
            int goalRow = (gp.bunny.worldY)/gp.tileSize;
            //System.out.println("goal row = " + goalRow);
            searchShortestPath(goalCol, goalRow, worldX, worldY);

        }else {
            actionLockCounter++;
            if (actionLockCounter == 30) {
                Random random = new Random();
                int i = random.nextInt(100) + 1;// pick up a number from 1 to 100

                if (i <= 25) {
                    direction = "up";
                }
                if (i > 25 && i <= 50) {
                    direction = "down";
                }
                if (i > 50 && i <= 75) {
                    direction = "left";
                }
                if (i > 75 && i < 100) {
                    direction = "right";
                }
                actionLockCounter = 0;
            }
        }

    }
    /**This method will update wolf direction, wolf collision and check the distance between
     * wolf and bunny.
     */
    public void update(){   

        IsCollison = false; //change start
        gp.checker.check(this);
        int wolfIndex = gp.checker.checkWolf(gp.bunny, gp.wolf);
        gp.bunny.bunnyDie(wolfIndex);

        if(!IsCollison){
            switch(direction){
                case "up":
                    worldY -= speed;
                    //System.out.println("wolf position y " + worldY);
                    break;
                case "down":
                    worldY += speed;
                    //System.out.println("wolf position y " + worldY);
                    break;
                case "left":
                    worldX -= speed;
                    //System.out.println("wolf position X " + worldX);
                    break;
                case "right":
                    worldX += speed;
                    //System.out.println("wolf position X " + worldX);
                    break;
            }
        }
        setAction();

        spriteCounter ++;
        //can adjust this 10 so the animation can be smoother
        if (spriteCounter > 10){
            if (spriteNum == 1){
                spriteNum = 2;
            } else if (spriteNum == 2) {
                spriteNum =1;
            }
            //reset the counter
            spriteCounter = 0;
        }

        int xDistance = Math.abs(gp.bunny.worldX - worldX);
        int yDistance = Math.abs(gp.bunny.worldY - worldY);
        if (onPath == false && xDistance <= 100  && yDistance <= 100){
            onPath = true;
        }
        if (onPath == true && xDistance > 100  && yDistance > 100) {
            onPath = false;
        }


    }
    /**This method will draw the wolf into our GamePanel
     * @param g2 This is image of wolf
     * @param gp This is our gamePanel
     */
    public void draw(Graphics2D g2, GamePanel gp){
        BufferedImage image = null;
        switch (direction){
            case "up":
                if (spriteNum == 1 ){
                    image = up1;
                }
                if (spriteNum == 2 ){
                    image = up2;
                }
                break;

            case "down":
                if (spriteNum == 1 ){
                    image = down1;
                }
                if (spriteNum == 2 ){
                    image = down2;
                }
                break;

            case "left":
                if (spriteNum == 1 ){
                    image = left1;
                }
                if (spriteNum == 2 ){
                    image = left2;
                }
                break;

            case "right":
                if (spriteNum == 1 ){
                    image = right1;
                }
                if (spriteNum == 2 ){
                    image = right2;
                }
                break;
        }

        /** drawing the wolf */
        g2.drawImage(image, worldX, worldY, gp.wolftileWidth, gp.WolftileHeight, null);
    }
}
