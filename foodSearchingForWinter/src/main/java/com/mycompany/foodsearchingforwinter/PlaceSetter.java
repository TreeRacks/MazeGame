package com.mycompany.foodsearchingforwinter;

import Objects.Wolf;
import Punishment.*;
import StaticObject.All_Reward;
import StaticObject.Door;
import StaticObject.Reward_carrot;
import StaticObject.Reward_medkit;

/**
 * This class is for setting object positions
 */
public class PlaceSetter {
    GamePanel gp;
    utilityTool utool;
    /**Constructor initialze game panel
     * @param gp need to have gamepanel
     */
    public PlaceSetter(GamePanel gp){
        this.gp = gp;
        this.utool = new utilityTool(gp);
    } 

    /**
     * This method sets the position of objects in the reward class in map
     */
    public void setThing(){
        //carrots
        gp.the_rewards[0] = new Reward_carrot();
        utool.setRewards(gp.the_rewards[0], 6, 9);

        gp.the_rewards[1] = new Reward_carrot();
        utool.setRewards(gp.the_rewards[1], 12, 12);

        gp.the_rewards[2] = new Reward_carrot();
        utool.setRewards(gp.the_rewards[2], 20, 5);
       
        gp.the_rewards[3] = new Reward_carrot();
        utool.setRewards(gp.the_rewards[3], 24, 14);

        gp.the_rewards[4] = new Reward_carrot();
        utool.setRewards(gp.the_rewards[4], 6, 2);

        //Door
        gp.the_rewards[5] = new Door();
        utool.setRewards(gp.the_rewards[5], 25, 2);

        //medkit
        
        gp.the_rewards[6] = new Reward_medkit();
        utool.setRewards(gp.the_rewards[6], 3, 14);
        
        gp.the_rewards[7] = new Reward_medkit();
        utool.setRewards(gp.the_rewards[7], 20, 8);


        //spoiledcarrot
        gp.the_rewards[8] = new spoiled_carrot();
        utool.setRewards(gp.the_rewards[8], 6, 14);

        gp.the_rewards[9] = new spoiled_carrot();
        utool.setRewards(gp.the_rewards[9], 3, 8);

        gp.the_rewards[10] = new spoiled_carrot();
        utool.setRewards(gp.the_rewards[10], 20, 13);

        gp.the_rewards[11] = new spoiled_carrot();
        utool.setRewards(gp.the_rewards[11], 18, 5);

    }

    /**This method will set the position of wolf
     */
    public void setWolf(){
        gp.wolf[0] = new Wolf(gp);
        gp.wolf[0].worldX = 50;
        gp.wolf[0].worldY = 50;

        gp.wolf[1] = new Wolf(gp);
        gp.wolf[1].worldX = 350;
        gp.wolf[1].worldY = 400;

        gp.wolf[2] = new Wolf(gp);
        gp.wolf[2].worldX = 800;
        gp.wolf[2].worldY = 400;
    }
    
}
