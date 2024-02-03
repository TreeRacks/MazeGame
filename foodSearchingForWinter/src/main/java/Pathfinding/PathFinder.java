package Pathfinding;

import com.mycompany.foodsearchingforwinter.GamePanel;

import java.util.ArrayList;

    /* 
        A PathFinder object represents the game's matrix of nodes. 
        This class is responsible for setting and handling individual nodes to be used for the A* algorithm
        to find the shortest path between two objects.
    */
public class PathFinder {
    GamePanel  gp;
    public Node[][] node;
    ArrayList<Node> openList = new ArrayList<>();
    public ArrayList<Node> pathList = new ArrayList<>();
    public Node startNode, goalNode, currentNode;
    boolean reachedGoal = false;
    int step = 0;

    public PathFinder(GamePanel gp){
        this.gp = gp;
        instantiateNodes();
    }

    public void instantiateNodes() {
        node = new Node[gp.maxScreenCol][gp.maxScreenRow];

        int col = 0;
        int row = 0;

        while(col < gp.maxScreenCol && row < gp.maxScreenRow){
            node[col][row] = new Node(col, row);
            col++;

            if (col == gp.maxScreenCol){
                col = 0;
                row++;
            }
        }
    }

    public void resetNode(){
        int col = 0;
        int row = 0;

        while(col < gp.maxScreenCol && row < gp.maxScreenRow){
            /** reset the nodes */
            node[col][row].open = false;
            node[col][row].checked = false;
            node[col][row].solid = false;
            col++;

            if (col == gp.maxScreenCol){
                col = 0;
                row++;
            }
        }
        /** reset everything else */
        openList.clear();
        pathList.clear();
        reachedGoal = false;
        step = 0;
    }

    public void setNode(int startCol, int startRow, int goalCol, int goalRow){
        resetNode();

        /** set the start and goal nodes */
        startNode = node[startCol][startRow];
        startNode.setAsStart();
        startNode.col = startCol;
        startNode.row = startRow;

        currentNode = startNode;

        goalNode =node[goalCol][goalRow];
        goalNode.setAsGoal();
        goalNode.col = goalCol;
        goalNode.row = goalRow;
        //and add the current node on the list
        openList.add(currentNode);

        //System.out.println("Start node row = "+ startNode.row);
        //System.out.println("Start node col = "+ startNode.col);
        //System.out.println("goal node row = "+ goalNode.row);
        //System.out.println("goal node col = "+ goalNode.col);

        int col = 0;
        int row = 0;

        while(col < gp.maxScreenCol && row < gp.maxScreenRow){

            int tileNum = gp.tileM.mapTileCord[col][row];
            if (gp.tileM.tile[tileNum].collision == true){
                node[col][row].solid = true;
            }
            getCost(node[col][row]);
            col++;
            if (col == gp.maxScreenCol){
                col = 0;
                row++;
            }
        }
    }

    public void getCost(Node node){
        int xDistant = Math.abs(node.col - startNode.col);
        int yDistant = Math.abs(node.row - startNode.row);
        node.gCost = xDistant + yDistant;

        xDistant = Math.abs(node.col - goalNode.col);
        yDistant = Math.abs(node.row - goalNode.row);
        node.hCost = xDistant + yDistant;

        node.fCost = node.hCost + node.gCost;


    }

    public boolean searchForPath(){
        while (reachedGoal == false && step < 500){
            int col = currentNode.col;
            int row = currentNode.row;

            // to check the current node and remove from the unchecked list
            currentNode.checked = true;
            openList.remove(currentNode);

            //System.out.println("row = " + row);
            //System.out.println("col = " + col);

            // open the up node
            if (row - 1 >= 0 ){
                openNode(node[col][row - 1]);
            }
            // left node
            if (col - 1 >= 0 ){
                openNode(node[col - 1][row]);
            }
            // down node
            if (row + 1 < gp.maxScreenRow ){
                openNode(node[col][row + 1]);
            }
            //right node
            if (col + 1 < gp.maxScreenCol ){
                openNode(node[col + 1][row]);
            }

            /** to find the best path */
            int bestNodeIndex = 0 ;
            int bestNodefCost = 999;
            //System.out.println("open list size: " + openList.size());
            scanListForBestPath(bestNodeIndex, bestNodefCost);
            
            if (openList.isEmpty()){
                break;
            }
            currentNode = openList.get(bestNodeIndex);
            //System.out.println("open list size after the loop: " + openList.size());


            if (currentNode == goalNode) {
                reachedGoal = true;
                pathTracking();
            }
            step++;
        }
        //System.out.println("reached goal = " + reachedGoal);
        return reachedGoal;
    }
    
    public void scanListForBestPath(int bestNodeIndex, int bestNodefCost){
        //scan the openlist to find the best one
            for (int i = 0; i < openList.size(); i++){
                //compare f cost
                if (openList.get(i).fCost < bestNodefCost){
                    bestNodeIndex = i;
                    bestNodefCost = openList.get(i).fCost;
                }
                //if F cost is the same, what about G cost
                else if (openList.get(i).fCost == bestNodefCost){
                    if (openList.get(i).gCost < openList.get(bestNodeIndex).gCost){
                        bestNodeIndex = i;
                    }
                }
            }
    }

    public void openNode(Node node){

        if (node.open == false && node.checked == false && node.solid == false){
            node.open = true;
            node.parent = currentNode;
            openList.add(node);
            //System.out.println("Open node added");
        }
    }
    public void pathTracking() {
        Node current = goalNode;

        //System.out.println("current node col in tracking: " + current.col);
        //System.out.println("current node row in tracking: " + current.row);
        //System.out.println("start node col in tracking: " + startNode.col);
        //System.out.println("start node row in tracking: " + startNode.row);

        while (current != startNode){
            pathList.add(0, current);
            current = current.parent;
            //System.out.println("pathlist size" + pathList.size());
        }
    }

}


