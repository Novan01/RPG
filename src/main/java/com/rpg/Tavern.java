package com.rpg;

import java.util.Scanner;
import com.rpg.character_classes.Character;
import com.rpg.npc_characters.TavernKeeper;

//import java.util.ArrayList;

public class Tavern {

	String food[] = { "Beef Stew,", "Turkey Legs" };
	String drinks[] = { "Meed", "Beer", "Water", "Wine" };
	TavernKeeper tk = new TavernKeeper();

	public void enterTavern(Character player) {
		barKeeperDialouge(player, drinks, food);
	}

	public void printMenu(String[] menu) {
		;
		for (int i = 0; i < menu.length; i++) {
			System.out.print(menu[i] + " ");
		}
		System.out.print("\n");
	}

	public void selectItem(String[] menu, Character player) {
		Scanner input = new Scanner(System.in); // scanner object to get what food they want
		// String user = input.nextLine();
		// boolean foodIsSelected = false;
		String choice = input.nextLine();
		for (int i = 0; i < menu.length; i++) {
			if (choice.equalsIgnoreCase(menu[i])) {
				System.out.println("Ah, the " + menu[i] + " ay? Alrighty then, that'll be x silver pieces");
				System.out.println("Are you sure you'd like the " + menu[i] + "? (Y or N)");
				choice = input.nextLine();
				if (choice.equalsIgnoreCase("Y")) {
					printMenu(menu);
					selectItem(menu, player);
				}
			}
		}

		input.close();

	}

	public void barKeeperDialouge(Character player, String[] drinks, String[] food) {
		System.out
				.println("Bar Keeper: Welcome to the Creaky Oak Tavern traveler, what are you known as in your lands?");
		System.out.println(player.getName() + ": Good evening, I am called " + player.getName());
		System.out.println(
				"Welcome " + player.getName() + " what could I do for you today? Food, Drink, a bed for rest?");
		tavernKeeperOptions(player, drinks, food);

	}

	public void tavernKeeperOptions(Character player, String[] drinks, String[] food) {
		Scanner option = new Scanner(System.in);
		String choice = option.nextLine();
		if (choice.equals("Food") || choice.equals("food")) {
			System.out.println("Oh you'd like some food ay? Well let me show you our menu");
			printMenu(food);
			System.out.println("What would you like?");
			selectItem(food, player);
		}
		if (choice.equals("Drink") || choice.equals("drink")) {

			System.out.println("Ah you'd like a drink ay? Well, let me show you our menu");
			printMenu(drinks);
			System.out.println("What would you like?");
			selectItem(drinks, player);

		}

		option.close();
	}

}
