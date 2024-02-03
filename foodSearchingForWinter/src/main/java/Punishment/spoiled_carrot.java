package Punishment;
import java.io.IOException;

import javax.imageio.ImageIO;

import StaticObject.All_Reward;

/**this class responsible for store punishment spoiled carrot */
public class spoiled_carrot extends All_Reward{
    /**initialize the name and read spoiled carrot image */
    public spoiled_carrot(){
        name = "spoiled_carrot";
    
    try{
        image = ImageIO.read(getClass().getResourceAsStream("/Punish/spoiled_carrot.png"));
    }catch(IOException e){
        e.printStackTrace();
    }
    }
}