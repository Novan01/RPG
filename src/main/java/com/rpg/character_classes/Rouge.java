package com.rpg.character_classes;
import com.rpg.extras.*;

public class Rouge extends Character {

	public String cName;
	public int cHealth;
	public int cStrength;
	public int cArmor;
	public int cStamina;

	Dice dice = new Dice();

	public Rouge() {
		cName = null;
		cHealth = 100;
		cStrength = 0;
		cArmor = 0;
		cStamina = 0;
	}

	public Rouge(String name, int strength, int armor, int stamina) {
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
		strength = dice.rollD10();
		return strength;
	}

	public int rollForArmor(int armor) {
		armor = dice.rollD8();
		return armor;
	}

	public int rollForStamina(int stamina) {
		stamina = dice.rollD20();
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
