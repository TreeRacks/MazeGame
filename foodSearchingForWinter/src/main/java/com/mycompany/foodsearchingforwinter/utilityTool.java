package com.mycompany.foodsearchingforwinter;

import StaticObject.All_Reward;

public class utilityTool {
   GamePanel gp;
   public utilityTool(GamePanel gp){
    this.gp = gp;
   } 
       /**This method is the tool to help set rewards to avoid
     * similar operation
     * @param reward extactly reward located
     * @param x reward x coodinate
     * @param y reward y coodinate
     */
    public void setRewards(All_Reward reward,int x,int y){
        reward.x = x*gp.tileSize;
        reward.y = y* gp.tileSize;
    }
}
