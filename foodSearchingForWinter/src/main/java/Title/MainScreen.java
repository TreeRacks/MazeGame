package Title;

import java.io.IOException;
import javax.imageio.ImageIO;

/**class for drawing the main title screen background */
public class MainScreen extends All_Titles {

    public MainScreen(){
        //collision = true;

        try{
            image = ImageIO.read(getClass().getResourceAsStream("/Title/MainScreen.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
    }



}

