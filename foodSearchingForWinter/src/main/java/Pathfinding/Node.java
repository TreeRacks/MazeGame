package Pathfinding;

public class Node {
    /* 
        Each node object represents a certain position on the game map's matrix.
        This is primarily used to keep track of the distance between objects (such as the wolf and the bunny)
        and is used in the PathFinder class for the A* algorithm to find the shortest path.
    */
    Node parent;
    public int col;
    public int row;
    int gCost;
    int hCost;
    int fCost;
    public boolean solid;
    public boolean open;
    public boolean checked;
    public boolean start;
    public boolean goal;

    public Node(int col, int row){
        this.col = col;
        this.row = row;
    }
    public void setAsStart(){
        //System.out.println("This is the start");
        start = true;
    }

    public void setAsGoal(){
        //System.out.println("This is the goal");
        goal = true;
    }

    public void setAsOpen(){
        //System.out.println("THis node is open");
        open = true;
    }
    public void setAsChecked(){
        //System.out.println("THis node is checked");
        checked = true;
    }

}
