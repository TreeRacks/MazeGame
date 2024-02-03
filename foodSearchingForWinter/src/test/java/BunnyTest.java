
import org.junit.jupiter.api.Test;

import com.mycompany.foodsearchingforwinter.*;

import Objects.Bunny;
import StaticObject.All_Reward;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;

import javax.imageio.ImageIO;
import javax.swing.*;

public class BunnyTest {
    public GamePanel gp;
    public Control keyControlTest;

    public Bunny bunnyTest;
    @BeforeEach
    void setupBunny(){
        GamePanel gamePanel = new GamePanel();
        gp = gamePanel;
        Control keyControl = new Control(gamePanel);
        keyControlTest = keyControl;
        Bunny bunny = new Bunny(gp, keyControlTest);
        bunnyTest = bunny;
    }
    @Test
    void testSetBunnyBasic(){
        System.out.println("Testing the basic of the bunny");
        System.out.println("TESTING INIT LOCATION AND SPEED");
        assertTrue(bunnyTest.worldX == 100);
        assertTrue(bunnyTest.worldY == 100);
        assertTrue(bunnyTest.speed == 4);
        assertTrue(bunnyTest.direction.equals("down"));
    }
    @Test
    void testBunnyDie(){
        System.out.println("TESTING DYING");
        bunnyTest.bunnyDie(-5);
        assertTrue(bunnyTest.isDead == true);
    }
    @Test
    void testScore(){
        System.out.println("Testing score");
        assertTrue(bunnyTest.score == 0);
        assertTrue(bunnyTest.carrotNum == 0);
        assertTrue(bunnyTest.medkitNum == 0);
    //     // GamePanel gamePanel = new GamePanel();
    //     // Control keyControl = new Control(gamePanel);
    //     // PlaceSetter placeSetter = new PlaceSetter(gamePanel);
    //     // placeSetter.setThing();
    //     // Bunny bunny = new Bunny(gamePanel, keyControlTest);
    //     // int rewardsIndex;
    //     // for(int i = 0; i < gamePanel.the_rewards.length; i++){
    //     //     rewardsIndex = gamePanel.checker.checkRewards(bunny, true);
    //     //     bunny.pickUpRewards(rewardsIndex);
    //     //     if(gamePanel.the_rewards[i].name.equals("carrot")){
    //     //         bunnyTest.score+=5;
    //     //         assertEquals(bunnyTest.score, bunny.score);
    //     //     }
    //     //     if(gamePanel.the_rewards[i].name.equals("medkit")){
    //     //         bunnyTest.score+=10;
    //     //         assertEquals(bunnyTest.score, bunny.score);
    //     //     }
    //     //     if(gamePanel.the_rewards[i].name.equals("spoiled_carrot")){
    //     //         bunnyTest.score-=5;
    //     //         assertEquals(bunnyTest.score, bunny.score);
    //     //     }
    //     // }
    //     // GamePanel gamePanel = new GamePanel();
    //     // PlaceSetter theSetterTest = new PlaceSetter(gamePanel);
    //     // theSetterTest.setThing();
    //     // gamePanel.setUpGame();
    //     // bunnyTest=new Bunny(gamePanel,keyControlTest);
       
    //     // gp = new GamePanel();
    //     // PlaceSetter theSetter = new PlaceSetter(gp);
    //     // theSetter.setThing();
    //     // gp.setUpGame();
    //     // Bunny bunny = new Bunny(gp, keyControlTest);
    //     GamePanel gamePanel = new GamePanel();
    //     gp = gamePanel;
    //     PlaceSetter theSetter = new PlaceSetter(gamePanel);
    //     theSetter.setThing();
    //     Control keyControl = new Control(gamePanel);
    //     keyControlTest = keyControl;
    //     Bunny bunny = new Bunny(gp, keyControlTest);
    //     bunnyTest = bunny;

    //     // for(int i =0; i < gp.the_rewards.length; i++){
    //     //     if(gp.the_rewards[i].name.equals("carrot")){
    //     //         bunnyTest.score+=5;
    //     //         bunny.pickUpRewards(i);
    //     //         assertEquals(bunnyTest.score, bunny.score);
    //     //     }
    //         // if(gp.the_rewards[i].name.equals("medkit")){
    //         //     bunnyTest.score+=10;
    //         //     bunny.pickUpRewards(i);
    //         //     assertEquals(bunnyTest.score, bunny.score);
    //         // }
    //         // if(gp.the_rewards[i].name.equals("spoiled_carrot")){
    //         //     bunnyTest.score-=5;
    //         //     bunny.pickUpRewards(i);
    //         //     assertEquals(bunnyTest.score, bunny.score);
    //         // }
    //     }
     }


}
