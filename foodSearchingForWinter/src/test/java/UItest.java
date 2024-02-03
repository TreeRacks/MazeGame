
import org.junit.jupiter.api.Test;

import com.mycompany.foodsearchingforwinter.GamePanel;
import com.mycompany.foodsearchingforwinter.UI;

import Title.All_Titles;

import java.awt.Graphics2D;
import java.awt.image.*;
import java.io.IOException;

import javax.imageio.ImageIO;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;

public class UItest{
    public GamePanel gamePanel;
    public Graphics2D g2;
    @BeforeEach
    void setup(){
        GamePanel gamePanel = new GamePanel();
        UI ui = new UI(gamePanel);
    }
    @Test
    void test_showMessage(){
        GamePanel gamePanel = new GamePanel();
        String text = "Show message working";
        UI ui = new UI(gamePanel);
        ui.showMessage(text);
        assertEquals(text, ui.message);
    }
    
    @Test 
    void test_ImageLoad(){
        GamePanel gamePanel = new GamePanel();
        UI ui = new UI(gamePanel);
        try{
            ui.carrotImage = ImageIO.read(getClass().getResourceAsStream("/Reward/carrot3.png"));
            ui.medkitImage= ImageIO.read(getClass().getResourceAsStream("/Reward/medkit.png"));
            ui.mainScreenImage = ImageIO.read(getClass().getResourceAsStream("/Title/MainScreen.png"));
            ui.helpScreenImage = ImageIO.read(getClass().getResourceAsStream("/Title/Help.png"));
            System.out.println("image load correct");
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    @Test
    void test_gameFinished(){
        GamePanel gamePanel = new GamePanel();
        UI ui = new UI(gamePanel);
        assertEquals(0, ui.commandNum);
        assertEquals(0,ui.messageCounter);
        assertTrue(ui.messageOn == false);
        assertTrue(ui.gameFinished == false);
        assertTrue(ui.gameLoss == false);
    }

}