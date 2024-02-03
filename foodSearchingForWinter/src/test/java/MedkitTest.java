import org.junit.jupiter.api.Test;

import StaticObject.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class MedkitTest {
    @Test
    void name(){
        Reward_medkit c = new Reward_medkit();
        assertTrue(c.name.equals("medkit"));
        System.out.println("name correct");
    }

    @Test
    void collision(){
        Reward_medkit c = new Reward_medkit();
        assertTrue(c.collision == false);
        System.out.println("collison correct");
    }

    @Test
    void image_load(){
        try{
            Reward_medkit c = new Reward_medkit();
            c.image= ImageIO.read(getClass().getResourceAsStream("/Reward/medkit.png"));
            System.out.println("image load correct");
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
