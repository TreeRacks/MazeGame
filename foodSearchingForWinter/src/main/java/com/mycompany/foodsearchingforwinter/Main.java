/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.foodsearchingforwinter; 

import javax.swing.*;

/**the main class that runs this game */
public class Main {

    /**
     * the main() function 
     * @param args an array of string
     */
     public static void main(String[] args) {
        System.out.println("Hello World!");

        //creating new frame
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Food Searching for Winter");// this is the title of the window



        // game panel create
        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);
        window.pack();


        window.setLocationRelativeTo(null);
        window.setVisible(true);
        gamePanel.setUpGame();
        gamePanel.startGameThread();
    }
}
