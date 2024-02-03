
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;

import com.mycompany.foodsearchingforwinter.GamePanel;
import com.mycompany.foodsearchingforwinter.CollisonCheck;
import Objects.*;
import com.mycompany.foodsearchingforwinter.Control;




public class CollisonCheckTest {
   
    @Test
    void reward_collison(){ 
        GamePanel gamePanel = new GamePanel();
        CollisonCheck checker = new CollisonCheck(gamePanel);
        Control keyControl = new Control();
        Bunny bunny = new Bunny(gamePanel,keyControl);
        int num = checker.checkRewards(bunny, true);

        assertEquals(999,num);
        System.out.println("reward_collison success");
    }

    @Test
    void wolf_collison(){
        GamePanel gamePanel = new GamePanel();
        CollisonCheck checker = new CollisonCheck(gamePanel);
        Control keyControl = new Control();
        Bunny bunny = new Bunny(gamePanel,keyControl);
        Wolf wolf[] = new Wolf[3];
        int num = checker.checkWolf(bunny,wolf);
        assertEquals(999,num);
        System.out.println("wolf_collison success");
    }


    
}
