package StaticObject;
import java.io.IOException;
import javax.imageio.ImageIO;

/**this class store bonus reward medkit */
public class Reward_medkit extends All_Reward {
    /**initialze the name and read medkit image */
    public Reward_medkit(){
        name = "medkit";
        //collision = true;

        try{
            image = ImageIO.read(getClass().getResourceAsStream("/Reward/medkit.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
