package StaticObject;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

/**This class is responsible for reading the door image and put in map */

public class Door extends All_Reward {
     /**Constructor read image,initialize name, and set collison to true */
     public Door(){
         name = "door";
         collision = true;
 
         try{
             image = ImageIO.read(getClass().getResourceAsStream("/Reward/door.png"));
         }catch(IOException e){
             e.printStackTrace();
         }
     }
}
