package com.rpg.extras;

import java.util.Random;


public class Dice {

	Random generator = new Random();

	public int rollD3() {
		int d3 = generator.nextInt(3) + 1;
		return d3;
	}

	public int rollD6() {
		int d6 = generator.nextInt(6) + 1;
		return d6;
	}

	public int rollD8() {
		int d8 = generator.nextInt(8) + 1;
		return d8;
	}

	public int rollD10() {
		int d10 = generator.nextInt(10) + 1;
		return d10;
	}

	public int rollD20() {
		int d20 = generator.nextInt(20) + 1;
		return d20;
	}


}
