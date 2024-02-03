package Objects;

import com.mycompany.foodsearchingforwinter.Control;
import com.mycompany.foodsearchingforwinter.GamePanel;
import com.mycompany.foodsearchingforwinter.utilityTool;

import StaticObject.Reward_medkit;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

/**
 * This is the bunny player class
 */
public class Bunny extends Object{
    Control keyControl;
    utilityTool utool;

    /** the total score player has */
    public int score = 0;
    /** the total carrots player gets */
    public int carrotNum = 0;
    /** the total medkit player gets*/
    public int medkitNum = 0;

    public boolean isDead = false;

    /**The constructor sets basic position of player bunny
     * and read the bunny image
     * @param gp need GamePanel object
     * @param keyControl need Control object that manages the keyborad movement
     */
    public Bunny (GamePanel gp, Control keyControl){
        super(gp);
        this.keyControl = keyControl;
        this.utool = new utilityTool(gp);

        //we should use this because we need the collision box
        solidArea = new Rectangle(8 , 5, 32 , 30);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        setBunnyBasic();
        getBunnyPixel();

    }


    /**This method is setting the basic atrribution of bunny such as initial x and y coodinates, the speed of movement
     * and its direction
     */
    public void setBunnyBasic(){

        worldX = 100;
        worldY = 100;
        speed = 4;
        direction = "down";
    }

    /**This method is getting the bunny pixel from the resource
     */
    public void getBunnyPixel() {
        try {
            up1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Bunny/up1.png")));
            up2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Bunny/up2.png")));
            down1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Bunny/down1.png")));
            down2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Bunny/down2.png")));
            right1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Bunny/right1.png")));
            right2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Bunny/right2.png")));
            left1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Bunny/left1.png")));
            left2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Bunny/left2.png")));

        }catch (IOException e){
            e.printStackTrace();
        }
    }
    /**This method sets the direction of bunny when user press the WASD keys in 
     * the keyboard and check whether there is a collision with other object
     * in the map.
     */
    public void update(){
        
        if(keyControl.isUp==true || keyControl.isDown==true ||keyControl.isLeft==true ||keyControl.isRight==true){
            if (keyControl.isUp){
                direction = "up";
                //ypo -= speed;  //change
            }
            else if (keyControl.isDown){
                direction = "down";
                //ypo += speed;
            }
            else if (keyControl.isLeft){
                direction = "left";
                //xpo -= speed;
            }
            else if (keyControl.isRight){
                direction = "right";
                //xpo += speed;
            }

            IsCollison = false; //change start
            gp.checker.check(this);

            //check rewards collision
            int rewardsIndex = gp.checker.checkRewards(this, true);
            pickUpRewards(rewardsIndex);

            /** check wolf collision */
            int wolfIndex = gp.checker.checkWolf(this, gp.wolf);
            bunnyDie(wolfIndex);

            //if no collison happen, then move
            if(IsCollison==false){
              no_collison_movement(direction);      
            }

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
        }
    }

    
    /**When no collision happen player position changes according
    * to it's directions
    *@param direc the direction of the player
    */
    public void no_collison_movement(String direc){
        switch(direc){
            case "up":
                worldY -= speed;
                //random_carrot2();
                break;
            case "down":
                worldY += speed;
                random_medkit1();
                break;
            case "left":
                worldX -= speed;
                random_medkit2();
                break;
            case "right":
                worldX += speed;
                random_medkit3();
                break;
        }
    }

    /**This method will remove the rewards from the rewards list if bunny
     * reach the position, otherwise it will not do anything
     * @param i the position of the reward in the rewards list
     */
    public void pickUpRewards(int i){
        if(i != 999){
            String rewardType = gp.the_rewards[i].name;

            switch (rewardType){
                case "carrot":
                    score+=5;//one carrot for adding 5 points
                    carrotNum++;
                    gp.the_rewards[i] = null;
                    gp.ui.showMessage("You got a carrot!");
                    break;
                case "spoiled_carrot":
                    score-=5;//one carrot for decreasing 5 points
                    gp.the_rewards[i] = null;
                    gp.ui.showMessage("Punishment! you got a spoiled carrot!");
                    if (score<0){
                        gp.ui.gameLoss = true;
                    }
                    break;
                case "medkit":
                    score+=10;//one medkit for adding 10 points
                    gp.the_rewards[i] = null;
                    medkitNum++;
                    gp.ui.showMessage("You got a medkit!");
                    break;
                case "door":
                    if(carrotNum == 5){
                        gp.ui.gameFinished = true;
                        gp.the_rewards[i] = null;
                    }
                    else if(carrotNum < 5)
                        gp.ui.showMessage("You need more carrots!");
                    break;
            }
        }
    }

    public void bunnyDie(int index){
        if ( index != 999){
            score -= 50;
            if (score<0){
                gp.ui.gameLoss = true;
                isDead = true;
            }
            else {isDead = false;}
            System.out.println("Bunny touched the wolf, bunny dies!\n");
        }
    }

    /**
     * This method used to import and draw the image
     * @param g2 the grahics use to draw images on map
     */
    public void draw(Graphics g2){
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
        //importing the pixel
        g2.drawImage(image, worldX, worldY, gp.bunnytileWidth, gp.bunnytileHeight, null);

    }
    /**
     * funstion to set medkit position
     */
    private void random_medkit1(){
        if(worldY<300 && worldX<240){ 
            gp.the_rewards[12]=new Reward_medkit();
            utool.setRewards(gp.the_rewards[12], 7, 6);

            gp.the_rewards[6] = null;
        }
        else if (worldY>300 && worldY<576 && worldX<500){
            gp.the_rewards[12]=null;

            gp.the_rewards[6] = new Reward_medkit();
            utool.setRewards(gp.the_rewards[6], 3, 14);
        }
        else if(worldY<150 && worldX>500 && worldX<1000){
            gp.the_rewards[13]=new Reward_medkit();
            utool.setRewards(gp.the_rewards[13], 16, 5);
        }
    }

    /**function to set random medkit position */
    private void random_medkit2(){
        if(worldY<300 && worldX>500 && worldX<1000){ 

            gp.the_rewards[7] = null;
        }
        else if (worldY>300 && worldY<400 && worldX>500){
            gp.the_rewards[12]=null;
            gp.the_rewards[13] = null;

        }
        else if (worldX>500){
            gp.the_rewards[7]=null;
        }
    }

    /** function to set random medkit position */
    private void random_medkit3(){
        if(worldX>700 && worldX<900){ 
            gp.the_rewards[6] = null;
            //gp.the_rewards[13] =null;

            gp.the_rewards[14] = new Reward_medkit();
            utool.setRewards(gp.the_rewards[14], 20, 14);
        }
        else if (worldX<700){
            gp.the_rewards[14] = null;
        }
    }
}

