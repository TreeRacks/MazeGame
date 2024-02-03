import org.junit.jupiter.api.Test;

import StaticObject.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;


public class CarrotTest {
    @Test
    void name(){
        Reward_carrot c = new Reward_carrot();
        assertTrue(c.name.equals("carrot"));
        System.out.println("name correct");
    }

    @Test
    void collision(){
        Reward_carrot c = new Reward_carrot();
        assertTrue(c.collision == false);
        System.out.println("collison correct");
    }

    @Test
    void image_load(){
        try{
            Reward_carrot c = new Reward_carrot();
            c.image= ImageIO.read(getClass().getResourceAsStream("/Reward/door.png"));
            System.out.println("image load correct");
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
