package com.rpg.character_classes;
import com.rpg.extras.*;

public class Warrior extends Character {

	public String cName;
	public int cHealth;
	public int cStrength;
	public int cArmor;
	public int cStamina;

	Dice dice = new Dice();

	public Warrior() {
		cName = null;
		cHealth = 100;
		cStrength = 0;
		cArmor = 0;
		cStamina = 0;
	}

	public Warrior(String name, int strength, int armor, int stamina) {
		cName = name;
		cHealth = 100;
		cStrength = strength;
		cArmor = armor;
		cStamina = stamina;

	}

	public String getName() {
		return cName;
	}

	public int getHealth() {
		return cHealth;
	}

	public int getStrength() {
		return cStrength;
	}

	public int getArmor() {
		return cArmor;
	}

	public int getStamina() {
		return cStamina;
	}

	public int rollForStrength(int strength) {
		strength = dice.rollD20();
		return strength;
	}

	public int rollForArmor(int armor) {
		armor = dice.rollD10();
		return armor;
	}

	public int rollForStamina(int stamina) {
		stamina = dice.rollD6();
		return stamina;
	}

	public void printStats() {
		System.out.println("Here's your characters stats");
		System.out.println("Name: " + this.getName());
		System.out.println("Health: " + this.getHealth());
		System.out.println("Strength: " + this.getStrength());
		System.out.println("Armor: " + this.getArmor());
		System.out.println("Stamina: " + this.getStamina());
	}
}
