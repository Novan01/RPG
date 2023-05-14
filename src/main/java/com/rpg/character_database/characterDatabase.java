package com.rpg. character_database;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rpg.character_classes.Character;
import java.util.ArrayList;
import java.io.*;


/*
 * 
 * Lets use an ArrayList<Character> to hold each new character as they are added in, this list will also be displayed when Play is pressed so the player can select their character
 * 
 */


public class characterDatabase {
    ArrayList<Character> characterList = new ArrayList<Character>();
    String fileName = "characterDatabase.json";
   
   
    public characterDatabase() {
        characterList = loadCharactersFromFile();
    }
    
    //method to retrieve character from the .dat file - should be based on the character name
    public void addCharacterToList(Character player) throws JsonProcessingException {
        characterList.add(player);
        System.out.println(player.getCharClass());

        //serialize the character list into json
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(characterList);
       

        //write json to the file
        try(FileWriter fw = new FileWriter(fileName)) {
            fw.write(json);
        } catch (Exception e) {
            System.out.println("Failed to save character to " + e.getMessage());
        }
    }

    public void displayCharacterList(ArrayList<Character> characterList) {
        
    }

    public ArrayList<Character> getAllCharacters() {
        return this.characterList;
    }

    public ArrayList<Character> loadCharactersFromFile() {
        
        ArrayList<Character> loadedCharacters = new ArrayList<>();
       
        try {
            File file = new File(fileName);
            ObjectMapper om = new ObjectMapper();
            om.registerSubtypes(Character.class);
            loadedCharacters = om.readValue(file, new TypeReference<ArrayList<Character>>(){});
        }
        catch(IOException e) {
            System.out.println("Failed to load characters " + e.getMessage());
        }
        return loadedCharacters;
    }
}