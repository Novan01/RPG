package com.rpg.character_database;


import com.rpg.MainMenu;
import com.rpg.character_classes.Character;


import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.io.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

/*
 * This file will be displayed after pressing play that will pull up the list of created characters
 * Need to select character to play
 * delete character
 * and create new character if eneded
 */

public class CharacterSelector extends JPanel {

    //personal fonts
    private Font titleFont;
    private Font mainFont;

    //create frame, panel and scroll if needed
    private JFrame frame;
    private JPanel panel;
    private JScrollPane scrollPane;

    //labels 
    private JLabel selectLabel = new JLabel("Select Character", SwingConstants.CENTER);
    
    //needed lists and database
    private JList<String> characterList;
    //eventually create some login page 
    private characterDatabase cd = new characterDatabase();

    //buttons to be added to frame
    private JButton createChar = new JButton("New");
    private JButton deleteChar = new JButton("Delete");
    private JButton selectChar = new JButton("Select");
    private JButton exitButton = new JButton("Exit");

    public CharacterSelector() {

        //set the custom font
        setFont();
        
        //get list from database
        ArrayList<Character> characters = cd.loadCharactersFromFile();
        
        //create new list model based on character names
        DefaultListModel<String> model = new DefaultListModel<>();
        for(Character character : characters) {
            model.addElement(character.getName());
        }

        //create the JList
        characterList = new JList<>(model);
        characterList.setFont(mainFont);

        //add scrollpane
        scrollPane = new JScrollPane(characterList);
        
        //create and initialize the frame
        frame = new JFrame("Character Select");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 800);

        //initialize panel
        panel = new JPanel(new BorderLayout());

        //character selection label
        
    
        //create button action listeners
        createChar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				new CharacterCreator();
                frame.setVisible(false);
			}
		});

        deleteChar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				if(characterList.getSelectedValue() != null) {
                    characterDatabase cd = new characterDatabase();
                    cd.deleteCharacter(characterList.getSelectedValue());

                    //update the JList
                    DefaultListModel<String> model = (DefaultListModel<String>) characterList.getModel();
                    model.removeElement(characterList.getSelectedValue());
                }
				
			}
		});

        selectChar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				
			}
		});

        exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				new MainMenu();
                frame.setVisible(false);
			}
		});
        

        //add title and panel
        panel.setBorder(new EmptyBorder(0, 100, 80, 80));
        panel.add(selectLabel, BorderLayout.NORTH);

        //size buttons
        selectChar.setSize(new Dimension(20, 40));


        //create button panel
        JPanel buttonPanel = new JPanel(new GridLayout(2, 2));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        buttonPanel.add(selectChar);
        buttonPanel.add(deleteChar);
        buttonPanel.add(createChar);
        buttonPanel.add(exitButton);

        //add to panel
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(buttonPanel,BorderLayout.SOUTH);

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setPreferredSize(new Dimension(600, 800));
		frame.add(panel);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
    }

    public void setFont() {
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

        selectLabel.setFont(titleFont);
        createChar.setFont(mainFont);
        selectChar.setFont(mainFont);
        exitButton.setFont(mainFont);
        deleteChar.setFont(mainFont);
        
    }
    
}