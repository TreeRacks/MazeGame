import org.junit.jupiter.api.Test;

import StaticObject.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;


public class DoorTest {
    
    @Test
    void name(){
        Door d = new Door();
        assertTrue(d.name.equals("door"));
        System.out.println("name correct");
    }

    @Test
    void collision(){
        Door d = new Door();
        assertTrue(d.collision == true);
        System.out.println("collison correct");
    }

    @Test
    void image_load(){
        try{
            Door d = new Door();
            d.image= ImageIO.read(getClass().getResourceAsStream("/Reward/door.png"));
            System.out.println("image load correct");
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    

}
