package com.rpg. character_database;
import com.rpg.character_classes.Character;
import java.util.ArrayList;
import java.io.*;


/*
 * 
 * Lets use an ArrayList<Character> to hold each new character as they are added in, this list will also be displayed when Play is pressed so the player can select their character
 * 
 */
import java.lang.reflect.Array;

public class characterDatabase {
    ArrayList<Character> characterList = new ArrayList<Character>();
    String fileName = "characterDatabase.txt";
   
   
    public characterDatabase() {

        //this.characterList = loadCharactersFromFile();
    }
    
    //method to retrieve character from the .dat file - should be based on the character name
    public void addCharacterToList(Character player) {
        characterList.add(player);
        System.out.println(player.getName());

        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName, true))) {
            oos.writeObject(player);
        }
        catch(IOException e) {
            System.out.println("Failed to add character to file " + fileName);
        }
    }

    public void displayCharacterList(ArrayList<Character> characterList) {
        
    }

    public ArrayList<Character> getAllCharacters() {
        return this.characterList;
    }

    public ArrayList<Character> loadCharactersFromFile() {
        
        ArrayList<Character> loadedCharacters = new ArrayList<>();
       
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            loadedCharacters = (ArrayList<Character>)ois.readObject();
        }
        catch(IOException | ClassNotFoundException e) {
            System.out.println("Failed to load characters " + e.getMessage());
        }
        return loadedCharacters;
    }
}