import Pathfinding.Node;
import Pathfinding.PathFinder;
import com.mycompany.foodsearchingforwinter.GamePanel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PathfindingTest {
    public Node testNode;
    public PathFinder testPath;

    @BeforeEach
    void setupPath(){
        GamePanel gp = new GamePanel();
        testPath = new PathFinder(gp);
        testPath.instantiateNodes();
        testPath.setNode(0,0,2,2);
    }

    @Test
    void checkInitPathNode(){
        assertTrue(testPath != null);
    }

    @Test
    void checkSetNode(){
        assertTrue(testPath.currentNode != null);
        assertTrue(testPath.goalNode != null);
        assertTrue(testPath.startNode != null);

        assertTrue(testPath.startNode.col == 0);
        assertTrue(testPath.startNode.row == 0);

        assertTrue(testPath.currentNode.col == 0);
        assertTrue(testPath.currentNode.row == 0);

        assertTrue(testPath.goalNode.col == 2);
        assertTrue(testPath.goalNode.row == 2);

    }

    @Test
    void checkSearch(){
        assertFalse(testPath.searchForPath() == true);
    }

}
