import Tile.Tile;
import Tile.TileManager;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

import javax.imageio.ImageIO;

import org.junit.jupiter.api.Test;

import com.mycompany.foodsearchingforwinter.GamePanel;

public class TileTest{
    GamePanel gamePanel = new GamePanel();
    TileManager tileManager = new TileManager(gamePanel);
     @Test
    void test_Map_TileLoad(){
        Tile[] tile = new Tile[15];
        try {
            //Grass
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Tile/grass1.png")));
            System.out.println("grass tile successfully read");
            //Sidewalk
            tile[1] = new Tile();
            tile[1].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Tile/sidewalk1.png")));
            System.out.println("sidewalk tile successfully read");
            //Water
            tile[2] = new Tile();
            tile[2].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Tile/water.png")));
            System.out.println("water tile successfully read");
            //GrassWall - Collision
            tile[3] = new Tile();
            tile[3].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Tile/grasswall.png")));
            tile[3].collision = true;
            System.out.println("water tile successfully read");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }    
    @Test
    void test_read_map(){
        int test_map[][] = new int[gamePanel.maxScreenCol][gamePanel.maxScreenRow];
        try {
            InputStream dir = getClass().getResourceAsStream("/MappingFile/tileMap.txt");//can be substitue with file path here
            BufferedReader buffer = new BufferedReader(new InputStreamReader(dir));//read text file

            int col = 0;
            int row = 0;

            while (col < gamePanel.maxScreenCol && row < gamePanel.maxScreenRow){//size of Map
                String line = buffer.readLine();//read a line of text

                while (col < gamePanel.maxScreenCol){
                    String number[] = line.split(" ");//Split string matching the format of text file

                    int num = Integer.parseInt(number[col]);
                    test_map[col][row] = num;//store each value
                    col++;
                }
                if (col == gamePanel.maxScreenCol){
                    col = 0;
                    row ++;//change to the next line/row
                }
            }
            dir.close();//close the file safely

        }catch (Exception e){
        }
        assertArrayEquals( test_map, gamePanel.tileM.mapTileCord);
    }
}