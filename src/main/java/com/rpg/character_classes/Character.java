package com.rpg.character_classes;

public class Character {
	public String cName;
	public int cHealth;
	public int cStrength;
	public int cDexterity;
	public int cIntelligence;
	public String cClass;

	public Character() {
		cName = null;
		cClass = null;
		cStrength = 0;
		cHealth = 0;
		cIntelligence = 0;
		cDexterity = 0;
		
	}

	public Character(String name, String charClass, int health, int strength, int dex, int intel) {
		cName = name;
		cClass = charClass;
		cHealth = health;
		cStrength = strength;
		cDexterity = dex;
		cIntelligence = intel;
		
	}

	public String getName() {
		return this.cName;
	}
	
	public String getCharClass() {
		return this.cClass;
	}

	public int getHealth() {
		return this.cHealth;
	}

	public int getStrength() {
		return this.cStrength;
	}

	public int getDexterity() {
		return this.cDexterity;
	}

	public int getIntelligence() {
		return this.cIntelligence;
	}


	//setters
	public void setName(String name) {
		this.cName = name;
	}

	public void setCharClass(String charClass) {
		this.cClass = charClass;
	}


}
