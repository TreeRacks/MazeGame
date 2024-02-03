import Objects.Wolf;
import com.mycompany.foodsearchingforwinter.GamePanel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class WolfTest {
    public GamePanel gp;
    public Wolf wolfTest;

    @BeforeEach
    void setWolfTest(){
        GamePanel gamePanel = new GamePanel();
        gp = gamePanel;
        Wolf wolf = new Wolf(gp);
        wolfTest = wolf;
    }

    @Test
    void testWolfBasic(){
        assertTrue(wolfTest.onPath == true);
        assertTrue(wolfTest.direction.equals("down"));
        assertTrue(wolfTest.speed == 1);
    }

}
