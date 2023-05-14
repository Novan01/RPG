package com.rpg;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.rpg.character_classes.Character;
import com.rpg.character_database.characterDatabase;
import com.rpg.extras.*;


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

public class CharacterSelector {

    private Font titleFont;
    private Font mainFont;

    private JFrame frame;
    private JPanel panel;

    //labels 
    private JLabel selectLabel = new JLabel("Select Character", SwingConstants.NORTH);

    private JButton createChar = new JButton("New Character");
    private JButton selectChar = new JButton("Select Character");
    private JButton exitButton = new JButton("Exit");

    public CharacterSelector() {
        
    }
    
}