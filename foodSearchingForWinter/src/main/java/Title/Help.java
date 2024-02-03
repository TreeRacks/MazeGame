package Title;

import java.io.IOException;
import javax.imageio.ImageIO;

/**class for drawing the main title screen background */
public class Help extends All_Titles {

    public Help(){
        //collision = true;

        try{
            image = ImageIO.read(getClass().getResourceAsStream("/Title/Help.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
    }



}

