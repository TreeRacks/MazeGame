
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.Assert.assertTrue;

import com.mycompany.foodsearchingforwinter.GamePanel;
import com.mycompany.foodsearchingforwinter.CollisonCheck;
import Objects.*;
import com.mycompany.foodsearchingforwinter.Control;
import java.awt.*; 

import java.awt.event.KeyEvent;


public class ControlTest {

    @Test
    void key_release(){
        GamePanel gamePanel = new GamePanel();
        Control keyControl = new Control(gamePanel);
        Button a = new Button("click");
        KeyEvent e = new KeyEvent(a, 1, 20, 1, 87, 'w');
        keyControl.keyReleased(e);
        assertTrue(keyControl.isUp == false);
        assertTrue(keyControl.isDown == false);
        assertTrue(keyControl.isLeft == false);
        assertTrue(keyControl.isRight == false);
    }

    
}
