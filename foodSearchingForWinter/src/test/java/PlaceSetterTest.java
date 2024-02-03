
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.mycompany.foodsearchingforwinter.GamePanel;
import com.mycompany.foodsearchingforwinter.PlaceSetter;
import Objects.*;

public class PlaceSetterTest {
    
    @Test
    void object_placement(){
        GamePanel gamePanel = new GamePanel();
        PlaceSetter theSetter = new PlaceSetter(gamePanel);
        theSetter.setThing();
        assertTrue(gamePanel.the_rewards[0] != null);
        assertTrue(gamePanel.the_rewards[1] != null);
        assertTrue(gamePanel.the_rewards[2] != null);
        assertTrue(gamePanel.the_rewards[5] != null);
        assertTrue(gamePanel.the_rewards[6] != null);
        assertTrue(gamePanel.the_rewards[8] != null);

    }

    @Test
    void wolf_placement(){
        GamePanel gamePanel = new GamePanel();
        PlaceSetter theSetter = new PlaceSetter(gamePanel);
        theSetter.setWolf();
        assertTrue(gamePanel.wolf[0] != null);
        assertTrue(gamePanel.wolf[1] != null);
        assertTrue(gamePanel.wolf[2] != null);
        

    }

}
