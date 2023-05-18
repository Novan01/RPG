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


public class CharacterCreator {

	Dice dice;
	//create labels and text fields for all Character attributes
	private JLabel nameLabel = new JLabel("Name", SwingConstants.LEFT);
	private JLabel strengthLabel = new JLabel("Strength", SwingConstants.LEFT);
	private JLabel classLabel = new JLabel("Class", SwingConstants.LEFT);
	private JLabel healthLabel = new JLabel("Health", SwingConstants.LEFT);
	private JLabel dexLabel = new JLabel("Dex", SwingConstants.LEFT);
	private JLabel intLabel = new JLabel("Intelligence", SwingConstants.LEFT);
	private JLabel creatorTitle = new JLabel("Character Creator", SwingConstants.CENTER);

	//text field for name
	public JTextField nameField = new JTextField(12);
	public JTextField strengthField = new JTextField(12);
	public JTextField classField = new JTextField(12); //will eventually be a class list
	public JTextField healthField = new JTextField(12);
	public JTextField dexField = new JTextField(12);
	public JTextField intField = new JTextField(12);

	

	//class list 
	public String classes[] = {"Warrior", "Rouge", "Wizard", "Bard"};
	private JList<String> classList = new JList<String>(classes);

	//scrollable panel for list
	JScrollPane scrollPane = new JScrollPane(classList);

	//font
	private Font mainFont;
	private Font titleFont;

	//frame and panell
	private JFrame frame = new JFrame("Character Creator");
    private JPanel panel = new JPanel(new BorderLayout());

	//buttons
	private JButton createButton = new JButton("Create");
	private JButton rollButton = new JButton("Roll");
	private JButton exitButton = new JButton("Exit");

	public CharacterCreator() {
		
		setFont();

		//button action listener
		createButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				//save the character to maybe json or sql or some custom database
				Character player = createCharacter();
				saveCharacter(player);	
				clearAllFields();
				frame.setVisible(false);
				new MainMenu();
			}
		});

		rollButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				//roll the characters stats - health, strength, dex
				setRandomFields(classList);
				
			}
		});

		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				new MainMenu();
                frame.setVisible(false);
			}
		});



		//add title to panel
		panel.setBorder(new EmptyBorder(0, 100, 80, 80));
		panel.add(creatorTitle, BorderLayout.NORTH);
		
		//create button panel
		JPanel buttonPanel = new JPanel(new GridLayout(1, 3));
		buttonPanel.add(rollButton);
		buttonPanel.add(createButton);
		buttonPanel.add(exitButton);
		panel.add(buttonPanel, BorderLayout.PAGE_END);

		//add labels
		JPanel labelPanel = new JPanel(new GridLayout(6, 1));
		labelPanel.add(nameLabel);
		labelPanel.add(classLabel);
		labelPanel.add(healthLabel);
		labelPanel.add(strengthLabel);
		labelPanel.add(dexLabel);
		labelPanel.add(intLabel);

		//dimension for text field size
		Dimension fieldSize = new Dimension(5, 5);

		//set the fields dimension
		nameField.setSize(fieldSize);
		classList.setSize(fieldSize);
		healthField.setSize(fieldSize);
		strengthField.setSize(fieldSize);
		dexField.setSize(fieldSize);
		intField.setSize(fieldSize);

		//set the fields to not editable
		healthField.setEditable(false);
		strengthField.setEditable(false);
		dexField.setEditable(false);
		intField.setEditable(false);


		//add fields to panel
		JPanel fieldPanel = new JPanel(new GridLayout(6, 1, 10, 30));
		fieldPanel.add(nameField);
		fieldPanel.add(scrollPane);
		fieldPanel.add(healthField);
		fieldPanel.add(strengthField);
		fieldPanel.add(dexField);
		fieldPanel.add(intField);
		//add the class list
		
		panel.add(labelPanel, BorderLayout.BEFORE_LINE_BEGINS);
		panel.add(fieldPanel, BorderLayout.AFTER_LINE_ENDS);

		//set up the frame
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setPreferredSize(new Dimension(600, 800));
		frame.add(panel);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}


	public void setRandomFields(JList<String> classList) {
		Dice dice = new  Dice();
		if(classList.getSelectedValue() == "Warrior") {
			healthField.setText(Integer.toString(dice.rollD20() + dice.rollD20()));
			strengthField.setText(Integer.toString(dice.rollD6() + dice.rollD6() + dice.rollD6()));
			dexField.setText(Integer.toString(dice.rollD6() + dice.rollD3()));
			intField.setText(Integer.toString(dice.rollD3()));

		}
		if(classList.getSelectedValue() == "Rouge") {
			healthField.setText(Integer.toString(dice.rollD10() + dice.rollD20()));
			strengthField.setText(Integer.toString(dice.rollD6() + dice.rollD6()));
			dexField.setText(Integer.toString(dice.rollD6() + dice.rollD10()));
			intField.setText(Integer.toString(dice.rollD6()));

		}
		if(classList.getSelectedValue() == "Wizard") {
			healthField.setText(Integer.toString(dice.rollD10() + dice.rollD10()));
			strengthField.setText(Integer.toString(dice.rollD10()));
			dexField.setText(Integer.toString(dice.rollD8()));
			intField.setText(Integer.toString(dice.rollD20()));

		}
		if(classList.getSelectedValue() == "Bard") {
			healthField.setText(Integer.toString(dice.rollD10() + dice.rollD6()));
			strengthField.setText(Integer.toString(dice.rollD6() + dice.rollD8()));
			dexField.setText(Integer.toString(dice.rollD6() + dice.rollD3()));
			intField.setText(Integer.toString(dice.rollD10() + dice.rollD8()));

		}
	}

	public static void saveCharacter(Character player) {
		characterDatabase cd = new characterDatabase();
		cd.addCharacterToList(player);
		
		
	}

	public Character createCharacter() {
		Character player = new Character(nameField.getText(), classList.getSelectedValue(), Integer.parseInt(healthField.getText()), Integer.parseInt(strengthField.getText()), Integer.parseInt(dexField.getText()), Integer.parseInt(intField.getText()));
		return player;
	}

	public void clearAllFields() {
		nameField.setText("");
		healthField.setText("");
		strengthField.setText("");
		dexField.setText("");
		intField.setText("");

	}

	public void setFont() {
		//create custom fonts for the title and game buttons
        try {
			titleFont = Font.createFont(Font.TRUETYPE_FONT, new File("lib/fonts/dragonwick-font/DragonwickBold-vmBZ.ttf")).deriveFont(32f);
            mainFont = Font.createFont(Font.TRUETYPE_FONT, new File("lib/fonts/dragonwick-font/DragonwickRegular-lgXV.ttf")).deriveFont(20f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(titleFont);
            ge.registerFont(mainFont);
        }
        catch(IOException | FontFormatException e) {

        }
		

		//set custom fonts to all fields and labels
		nameLabel.setFont(mainFont);
		strengthLabel.setFont(mainFont);
		classLabel.setFont(mainFont);
		healthLabel.setFont(mainFont);
		dexLabel.setFont(mainFont);
		intLabel.setFont(mainFont);
		createButton.setFont(mainFont);
		nameField.setFont(mainFont);
		healthField.setFont(mainFont);
		strengthField.setFont(mainFont);
		classList.setFont(mainFont);
		dexField.setFont(mainFont);
		intField.setFont(mainFont);
		rollButton.setFont(mainFont);
		creatorTitle.setFont(titleFont);
        exitButton.setFont(mainFont);

	}
}
