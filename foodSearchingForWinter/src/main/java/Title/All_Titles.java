package Title;

import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import com.mycompany.foodsearchingforwinter.GamePanel;

/**
 * This class is the parent class of all rewards
 */
public class All_Titles {

    /**
     * for reading images
     */
    public BufferedImage image;
    public int x, y;
    /**the position of object in an rectangle format  */
    public Rectangle solidArea = new Rectangle(0,0,48,48);
    /**defrault x positon */
    public int solidAreaDefault_x = 0;
    /**default y position */
    public int solidAreaDefault_y = 0;

    /**This method draws image on map
     * @param g2 Graphics2d
     * @param gp GamePanel
     */
    public void draw(Graphics2D g2, GamePanel gp){
        g2.drawImage(image, x,y, gp.tileSize, gp.tileSize, null);
    }

}