package StaticObject;

import java.io.IOException;
import javax.imageio.ImageIO;

/**this class repsonsible for carrot reward */
public class Reward_carrot extends All_Reward {

    /** constructor initialze the name and read image of carrots */
    public Reward_carrot(){
        name = "carrot";
        //collision = true;

        try{
            image = ImageIO.read(getClass().getResourceAsStream("/Reward/carrot3.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
    }



}

