package com.mycompany.foodsearchingforwinter;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.plaf.basic.BasicComboBoxUI.KeyHandler;

/**This class implements keylistener which It listens to the rest of the user's
 * input from the keyboard, which includes variables representing the four directions
 * of up, down, left and right. The movement of the character is controlled by pressing or
 * releasing the four keys representing the up, down, left and right.
 */
public class Control implements KeyListener {
    /** the boolean direction of the player */
    public boolean isUp, isDown, isRight, isLeft, isIdle;
    GamePanel gp;

    /**takes in gamepanel */
    public Control(GamePanel gp){
        this.gp = gp;
    }
    /**default do nothing */
    public Control() {
    }
    /**for key board handling */
    @Override
    public void keyTyped(KeyEvent e) {

    }

    /**This method will move immediately character  after user press the key which reperenst direction
     * it take the information which get from the keyboard
     * @param e the Direction Key entered by user from keyboard
     */
    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        //TITIE STATE
        if(gp.gameState == gp.titleState){
            gameTitleState(code);
        }

        //PLAY STATE
        if(gp.gameState == gp.playState) {
            gamePlayState(code);
        }
        //PAUSE STATE
            if (code == KeyEvent.VK_P) {
                pauseState();
        }           
        //END STATE
        if(gp.gameState == gp.endState){
            gameOverState(code);
        }
    }
    /**This is method for title screen key control that combine with UI class to make users interact keyboard with
     * program
     * This method use ui.commandNum to make options become a loop, which users can enter umlimited W/S key to move
     * allow because it will come back to first option when users press S key after allow is at last option
     * @param code this is user press code of key press, machine will recognize the code
     */
    public void  gameTitleState(int code){
        if (code == KeyEvent.VK_W){
            gp.ui.commandNum--;
            if(gp.ui.commandNum < 0){
                gp.ui.commandNum = 2;
            }
        }
        if (code == KeyEvent.VK_S){
            gp.ui.commandNum++;
            if(gp.ui.commandNum > 2){
                gp.ui.commandNum = 0;
            }
        }
       
        if(code == KeyEvent.VK_ENTER){
            if(gp.ui.commandNum == 0){ // user begins playing the game
                gp.gameState = gp.playState;
                gp.ui.gameLoss = false;
                gp.ui.play_time = 0;
                gp.restart();
                
            }
            if(gp.ui.commandNum == 1){ // do nothing
                
            }
            if(gp.ui.commandNum == 2){ // user leaves the game
                System.exit(0);
            }
        }
        /**this method will receive up,down,left,right code from keyboard,and
         * make object follow the action
         */
    }
    public void gamePlayState(int code){
        if (code == KeyEvent.VK_W) {
            isUp = true;
        }
        if (code == KeyEvent.VK_S) {
            isDown = true;
        }
        if (code == KeyEvent.VK_A) {
            isLeft = true;
        }
        if (code == KeyEvent.VK_D) {
            isRight = true;
        }
    }
    /**This method will swith game state bewteen play state and pause state 
     */
    public void pauseState(){
        if (gp.gameState == gp.playState) {
            gp.gameState = gp.pauseState;
        } else if (gp.gameState == gp.pauseState) {
            gp.gameState = gp.playState;
        }
    }
        /**This is method for end screen key control that combine with UI class to make users interact keyboard with
     * program
     * This method use ui.commandNum to make options become a loop, which users can enter umlimited W/S key to move
     * allow because it will come back to first option when users press S key after allow is at second option
     * @param code this is user press code of key press, machine will recognize the code
     */
    private void gameOverState(int code) {
        if (code == KeyEvent.VK_W) {
            gp.ui.commandNum--;
            if (gp.ui.commandNum < 0) {
                gp.ui.commandNum = 1;
            }
        }
        if (code == KeyEvent.VK_S) {
            gp.ui.commandNum++;
            if (gp.ui.commandNum > 1) {
                gp.ui.commandNum = 0;
            }
        }
        if(code == KeyEvent.VK_ENTER){
            if(gp.ui.commandNum == 0){
                if(gp.gameState == gp.endState){
                    gp.gameState = gp.playState;
                    gp.ui.play_time = 0;
                    gp.restart();
                    gp.ui.gameLoss = false;
                    System.out.println("gamestate is " + gp.gameState);
                }
            }
            else if(gp.ui.commandNum == 1){
                gp.gameState = gp.titleState;
            }
        }
    }

    /**This method will stop character action after user release the key, it take the
     * information which get from the keyboard
     * @param e the Direction Key entered by user from keyboard
     */
    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_W){
            isUp = false;
        }
        if (code == KeyEvent.VK_S){
            isDown = false;
        }
        if (code == KeyEvent.VK_A){
            isLeft = false;
        }
        if (code == KeyEvent.VK_D){
            isRight = false;
        }
    }
}
