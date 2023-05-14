package com.rpg.character_classes;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Character {

	@JsonProperty("name")
	private String name;
	@JsonProperty("health")
	private int health;
	@JsonProperty("strength")
	private int strength;
	@JsonProperty("dex")
	private int dex;
	@JsonProperty("intel")
	private int intel;
	@JsonProperty("charClass")
	private String charClass;

	public Character() {
		
		
	}

	public Character(String name, String charClass, int health, int strength, int dex, int intel) {
		this.name = name;
		this.charClass = charClass;
		this.health = health;
		this.strength = strength;
		this.dex = dex;
		this.intel = intel;
		
	}

	public String getName() {
		return name;
	}
	
	public String getCharClass() {
		return charClass;
	}

	public int getHealth() {
		return health;
	}

	public int getStrength() {
		return strength;
	}

	public int getDexterity() {
		return dex;
	}

	public int getIntelligence() {
		return intel;
	}


	//setters
	public void setName(String name) {
		this.name = name;
	}

	public void setCharClass(String charClass) {
		this.charClass = charClass;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public void setStrength(int strength) {
		this.strength = strength;
	}

	public void setDexterity(int dex) {
		this.dex = dex;
	}

	public void setIntelligence(int intelligence) {
		this.intel = intelligence;
	}

	@Override
	public String toString() {
		return "Character [name=" + name + ", class=" + charClass + ", health=" + health + ", strength=" + strength + ", dexterity=" + dex + ", intelligence=" + intel + "]"; 
	}

}
