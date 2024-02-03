package com.mycompany.foodsearchingforwinter; //change all below
import Objects.Object;

/**This class check the collison between objects */
public class CollisonCheck {
    GamePanel gp;
    
    /**initialize the GamePanel 
    * @param gp gamepanel
    */
    public CollisonCheck(GamePanel gp){
        this.gp = gp;
    }

    /**This method used to check for player's collison status
     * @param object any player that belong to the the object class
     */
    public void check(Object object){
        int obj_left_x = object.worldX + object.solidArea.x;
        int obj_right_x = object.worldX+ object.solidArea.x + object.solidArea.width;
        int obj_top_y = object.worldY + object.solidArea.y;
        int obj_bottom_y = object.worldY + object.solidArea.y + object.solidArea.height; //origin 60 but doesn't work

        //actual col # and row # in the 2d map
        int map_left_x = obj_left_x/gp.tileSize;
        int map_right_x = obj_right_x/gp.tileSize;
        int map_top_y = obj_top_y/47; //origin 60
        int map_bottom_y = obj_bottom_y/47;  //origin 60

        int pixel_num1, pixel_num2;

        switch(object.direction){
            case "up":
                map_top_y = (obj_top_y-object.speed)/47; //origin 60
                pixel_num1 = gp.tileM.mapTileCord[map_left_x][map_top_y];
                pixel_num2 = gp.tileM.mapTileCord[map_right_x][map_top_y];
                if(gp.tileM.tile[pixel_num1].collision==true || gp.tileM.tile[pixel_num2].collision==true ){
                    object.IsCollison=true;
                }
                break;
            case "down":
                map_bottom_y = ((obj_bottom_y+object.speed)/47); //origin 60
                pixel_num1 = gp.tileM.mapTileCord[map_left_x][map_bottom_y];
                pixel_num2 = gp.tileM.mapTileCord[map_right_x][map_bottom_y];
                if(gp.tileM.tile[pixel_num1].collision==true || gp.tileM.tile[pixel_num2].collision==true ){
                    object.IsCollison=true;
                }
                break;
            case "left":
                map_left_x = (obj_left_x-object.speed)/gp.tileSize;
                pixel_num1 = gp.tileM.mapTileCord[map_left_x][map_top_y];
                pixel_num2 = gp.tileM.mapTileCord[map_left_x][map_bottom_y];
                if(gp.tileM.tile[pixel_num1].collision==true || gp.tileM.tile[pixel_num2].collision==true ){
                    object.IsCollison=true;
                }
                break;
            case "right":
                map_right_x = (obj_right_x+object.speed)/gp.tileSize;
                pixel_num1 = gp.tileM.mapTileCord[map_right_x][map_top_y];
                pixel_num2 = gp.tileM.mapTileCord[map_right_x][map_bottom_y];
                if(gp.tileM.tile[pixel_num1].collision==true || gp.tileM.tile[pixel_num2].collision==true ){
                    object.IsCollison=true;
                }
                break;
        }
    }

    /**This method will check whether bunny will reach the rewards and return the
     * index of the reward in the reward list
     * @param object the bunny
     * @param bunny to judge if the object is bunny
     * @return the index of the reward
     */
    public int checkRewards(Object object, boolean bunny ){

        int index = 999;

        for(int i = 0; i < gp.the_rewards.length; i++){
            if(gp.the_rewards[i] != null){
                //get bunny solid area position
                object.solidArea.x = object.worldX +object.solidArea.x;
                object.solidArea.y = object.worldY +object.solidArea.y;
                // get the rewards solid area position
                gp.the_rewards[i].solidArea.x = gp.the_rewards[i].x + gp.the_rewards[i].solidArea.x;
                gp.the_rewards[i].solidArea.y = gp.the_rewards[i].y + gp.the_rewards[i].solidArea.y;

                switch (object.direction){
                    case "up":
                        object.solidArea.y -= object.speed;
                        if(object.solidArea.intersects(gp.the_rewards[i].solidArea)){
                            //check if it hits a door (not a reward)
                            if(gp.the_rewards[i].collision == true){
                                //player collison on 
                                object.IsCollison = true;
                            }
                            if(bunny == true){
                                index = i;
                            }
                        }
                        break;
                    case "down":
                        object.solidArea.y += object.speed;
                        if(object.solidArea.intersects(gp.the_rewards[i].solidArea)){
                            if(gp.the_rewards[i].collision == true){
                                object.IsCollison = true;
                            }
                            if(bunny == true){
                                index = i;
                            }
                        }
                        break;
                    case "left":
                        object.solidArea.x -= object.speed;
                        if(object.solidArea.intersects(gp.the_rewards[i].solidArea)){
                            if(gp.the_rewards[i].collision == true){
                                object.IsCollison = true;
                            }
                            if(bunny == true){
                                index = i;
                            }
                        }
                        break;
                    case "right":
                        object.solidArea.x += object.speed;
                        if(object.solidArea.intersects(gp.the_rewards[i].solidArea)){
                            if(gp.the_rewards[i].collision == true){
                                object.IsCollison = true;
                            }
                            if(bunny == true){
                                index = i;
                            }
                        }
                        break;
                }
                object.solidArea.x = object.solidAreaDefaultX;
                object.solidArea.y = object.solidAreaDefaultY;
                gp.the_rewards[i].solidArea.x = gp.the_rewards[i].solidAreaDefault_x;
                gp.the_rewards[i].solidArea.y = gp.the_rewards[i].solidAreaDefault_y;
            }
        }

        return index;
    }
    /**This method will find and check whether the bunny meet the wolf
     * @param bunny The user
     * @param wolf The move punishment
     * @return the index of the wolf
     */
    public int checkWolf(Object bunny, Object[] wolf){
        int index = 999;

        for(int i = 0; i < gp.wolf.length; i++){
            if(gp.wolf[i] != null){
                //get bunny solid area position
                bunny.solidArea.x = bunny.worldX +bunny.solidArea.x;
                bunny.solidArea.y = bunny.worldY +bunny.solidArea.y;
                // get the rewards solid area position
                gp.wolf[i].solidArea.x = gp.wolf[i].worldX + gp.wolf[i].solidArea.x;
                gp.wolf[i].solidArea.y = gp.wolf[i].worldY + gp.wolf[i].solidArea.y;

                switch (bunny.direction){
                    case "up":
                        bunny.solidArea.y -= bunny.speed;
                        break;
                    case "down":
                        bunny.solidArea.y += bunny.speed;
                        break;
                    case "left":
                        bunny.solidArea.x -= bunny.speed;
                        break;
                    case "right":
                        bunny.solidArea.x += bunny.speed;
                        break;
                }

                if(bunny.solidArea.intersects(gp.wolf[i].solidArea)){
                    if (wolf[i] != bunny) {
                        bunny.IsCollison = true;
                        index = i;
                    }
                }

                bunny.solidArea.x = bunny.solidAreaDefaultX;
                bunny.solidArea.y = bunny.solidAreaDefaultY;
                gp.wolf[i].solidArea.x = gp.wolf[i].solidAreaDefaultX;
                gp.wolf[i].solidArea.y = gp.wolf[i].solidAreaDefaultY;
            }
        }
        return index;
    }

}
