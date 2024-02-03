import Pathfinding.Node;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class NodeTest {
    public Node testNode;

    @BeforeEach
    void setupNode(){
        testNode = new Node(17,27);
        testNode.setAsGoal();
        testNode.setAsOpen();
        testNode.setAsChecked();
        testNode.setAsStart();
    }

    @Test
    void checkInitNode(){
        System.out.println("Checking the init value for the node");
        assertTrue(testNode.col == 17);
        assertTrue(testNode.row == 27);
    }

    @Test
    void checkCondition(){
        System.out.println("Checking the condition of the node");
        assertTrue(testNode.goal == true);
        assertTrue(testNode.checked == true);
        assertTrue(testNode.open == true);
        assertTrue(testNode.start == true);
    }
}
