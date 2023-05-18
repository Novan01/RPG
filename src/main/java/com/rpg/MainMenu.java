package com.rpg;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.io.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import com.rpg.character_database.CharacterSelector;
import com.rpg.character_database.MongoClientConnection;



public class MainMenu {

    //creating both fonts
    private Font mainFont;
    private Font titleFont;
    //create the labels for the game main menu screen
    private JLabel gameTitle = new JLabel("RPG", SwingConstants.CENTER);
    
    
    //create frame and panel
    private JFrame frame = new JFrame("Main Menu");
    private JPanel panel = new JPanel(new BorderLayout());

    //create the main menu screen buttons
    private JButton startGame = new JButton("Play");
    private JButton createChar = new JButton("New Character");
    private JButton exitButton = new JButton("Exit");


    public MainMenu() {
        
        //create custom fonts for the title and game buttons
        try {
            titleFont = Font.createFont(Font.TRUETYPE_FONT, new File("lib/fonts/dragonwick-font/DragonwickBold-vmBZ.ttf")).deriveFont(42f);
            mainFont = Font.createFont(Font.TRUETYPE_FONT, new File("lib/fonts/dragonwick-font/DragonwickRegular-lgXV.ttf")).deriveFont(21f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(titleFont);
            ge.registerFont(mainFont);
        }
        catch(IOException | FontFormatException e) {

        }

        //set the custom fonts for all object
        gameTitle.setFont(titleFont);
        startGame.setFont(mainFont);
        createChar.setFont(mainFont);
        exitButton.setFont(mainFont);

        //button action listeners
        startGame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
             //start the game  
             //first open character selection screen 
             new CharacterSelector();
             frame.setVisible(false);
            }
        });

        createChar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                //run the character creator
                new CharacterCreator();
                frame.setVisible(false);
            }
        });

        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                //close the game
                System.exit(0);
            }
        });

        //add title to the panel
        panel.setBorder(new EmptyBorder(80, 100, 80, 80));
        panel.add(gameTitle, BorderLayout.NORTH);
        JPanel buttonPanel = new JPanel(new GridLayout(3, 1, 20, 100));
        buttonPanel.add(startGame);
        buttonPanel.add(createChar);
        buttonPanel.add(exitButton);
        panel.add(buttonPanel, BorderLayout.CENTER);
		

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setPreferredSize(new Dimension(600, 800));
		frame.add(panel);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
    }
    
}
